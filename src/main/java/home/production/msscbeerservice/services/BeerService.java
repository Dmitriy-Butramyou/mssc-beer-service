package home.production.msscbeerservice.services;

import home.sfg.brewery.model.BeerDto;
import home.sfg.brewery.model.BeerPagedList;
import home.sfg.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
  BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

  BeerDto saveNewBeer(BeerDto beerDto);

  BeerDto updateBeer(UUID beerId, BeerDto beerDto);

  BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

  BeerDto getByUpc(String upc);
}
