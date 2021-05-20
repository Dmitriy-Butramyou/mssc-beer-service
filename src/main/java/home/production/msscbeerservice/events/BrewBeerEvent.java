package home.production.msscbeerservice.events;

import home.production.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent{

  public BrewBeerEvent(BeerDto beerDto) {
    super(beerDto);
  }
}
