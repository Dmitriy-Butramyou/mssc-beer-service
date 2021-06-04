package home.production.msscbeerservice.services.order;

import home.production.msscbeerservice.repositories.BeerRepositories;
import home.sfg.brewery.model.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

  private final BeerRepositories beerRepositories;

  public Boolean validateOrder(BeerOrderDto beerOrder) {

    AtomicInteger beersNotFound = new AtomicInteger();

    beerOrder.getBeerOrderLines().forEach(orderLine -> {
      if(beerRepositories.findByUpc(orderLine.getUpc()).isEmpty()) {
        beersNotFound.incrementAndGet();
      }
    });

    return beersNotFound.get() == 0;
  }
}
