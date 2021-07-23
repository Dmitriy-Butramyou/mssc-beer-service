package home.production.msscbeerservice.services.order;

import home.production.msscbeerservice.repositories.BeerRepository;
import home.sfg.brewery.model.events.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

  private final BeerRepository beerRepository;

  public Boolean validateOrder(BeerOrderDto beerOrder) {

    AtomicInteger beersNotFound = new AtomicInteger();

    beerOrder.getBeerOrderLines().forEach(orderline -> {
      if (beerRepository.findByUpc(orderline.getUpc()) == null) {
        beersNotFound.incrementAndGet();
      }
    });

    return beersNotFound.get() == 0;
  }
}
