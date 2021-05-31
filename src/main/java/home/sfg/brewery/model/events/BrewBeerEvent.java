package home.sfg.brewery.model.events;

import home.sfg.brewery.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent{

  public BrewBeerEvent(BeerDto beerDto) {
    super(beerDto);
  }
}
