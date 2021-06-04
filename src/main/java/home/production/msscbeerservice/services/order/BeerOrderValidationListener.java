package home.production.msscbeerservice.services.order;

import home.production.msscbeerservice.config.JmsConfig;
import home.sfg.brewery.model.events.ValidateOrderResult;
import home.sfg.brewery.model.events.ValidationOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

  private final BeerOrderValidator validator;
  private final JmsTemplate jmsTemplate;

  @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
  public void listen(ValidationOrderRequest validationOrderRequest) {
    Boolean isValid = validator.validateOrder(validationOrderRequest.getBeerOrder());

    jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
        ValidateOrderResult.builder()
            .isValid(isValid)
            .orderId(validationOrderRequest.getBeerOrder().getId())
            .build());
  }
}
