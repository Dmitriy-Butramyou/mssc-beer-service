package home.production.msscbeerservice.events;

import home.production.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

  public NewInventoryEvent(BeerDto beerDto) {
    super(beerDto);
  }
}
