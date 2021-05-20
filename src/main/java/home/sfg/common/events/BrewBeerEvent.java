package home.sfg.common.events;

import home.production.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent{

  public BrewBeerEvent(BeerDto beerDto) {
    super(beerDto);
  }
}
