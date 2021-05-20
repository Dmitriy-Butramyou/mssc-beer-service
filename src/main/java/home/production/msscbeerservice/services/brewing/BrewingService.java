package home.production.msscbeerservice.services.brewing;

import home.production.msscbeerservice.config.JmsConfig;
import home.production.msscbeerservice.domain.Beer;
import home.sfg.common.events.BrewBeerEvent;
import home.production.msscbeerservice.repositories.BeerRepositories;
import home.production.msscbeerservice.services.inventory.BeerInventoryService;
import home.production.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

  private final BeerRepositories beerRepositories;
  private final BeerInventoryService beerInventoryService;
  private final JmsTemplate jmsTemplate;
  private final BeerMapper beerMapper;

  @Scheduled(fixedRate = 5000)
  public void checkForLowInventory() {
    List<Beer> beers = beerRepositories.findAll();

    beers.forEach(beer -> {
      Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());

      log.debug("Min Onhand is: " + beer.getMinOnHand());
      log.debug("Inventory is: " + invQOH);

      if (beer.getMinOnHand() >= invQOH) {
        jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
      }
    });
  }
}
