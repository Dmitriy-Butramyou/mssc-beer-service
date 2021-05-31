package home.production.msscbeerservice.services;

import home.production.msscbeerservice.domain.Beer;
import home.production.msscbeerservice.repositories.BeerRepositories;
import home.production.msscbeerservice.web.controller.NotFoundException;
import home.production.msscbeerservice.web.mappers.BeerMapper;
import home.sfg.brewery.model.BeerDto;
import home.sfg.brewery.model.BeerPagedList;
import home.sfg.brewery.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

  private final BeerRepositories beerRepositories;
  private final BeerMapper beerMapper;

  @Cacheable(cacheNames = "beerCache", condition = "#showInventoryOnHand == false")
  @Override
  public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {

    if (showInventoryOnHand) {
      return beerMapper.beerToBeerDtoWithInventory(
          beerRepositories.findById(beerId).orElseThrow(NotFoundException::new)
      );
    } else {
      return beerMapper.beerToBeerDto(
          beerRepositories.findById(beerId).orElseThrow(NotFoundException::new)
      );
    }
  }

  @Cacheable(cacheNames = "beerUpcCache")
  @Override
  public BeerDto getByUpc(String upc) {
    return beerMapper.beerToBeerDto(beerRepositories.findByUpc(upc).orElseThrow(NotFoundException::new));
  }

  @Override
  public BeerDto saveNewBeer(BeerDto beerDto) {
    return beerMapper.beerToBeerDto(beerRepositories.save(beerMapper.beerDtoToBeer(beerDto)));
  }

  @Override
  public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
    Beer beer = beerRepositories.findById(beerId).orElseThrow(NotFoundException::new);

    beer.setBeerName(beerDto.getBeerName());
    beer.setBeerStyle(beerDto.getBeerStyle().name());
    beer.setPrice(beerDto.getPrice());
    beer.setUpc(beerDto.getUpc());

    return beerMapper.beerToBeerDto(beerRepositories.save(beer));
  }

  @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
  @Override
  public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {

    BeerPagedList beerPagedList;
    Page<Beer> beerPage;

    if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
      //search both
      beerPage = beerRepositories.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
    } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
      //search beer_service name
      beerPage = beerRepositories.findAllByBeerName(beerName, pageRequest);
    } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
      //search beer_service style
      beerPage = beerRepositories.findAllByBeerStyle(beerStyle, pageRequest);
    } else {
      beerPage = beerRepositories.findAll(pageRequest);
    }

    if (showInventoryOnHand) {
      beerPagedList = new BeerPagedList(beerPage
          .getContent()
          .stream()
          .map(beerMapper::beerToBeerDtoWithInventory)
          .collect(Collectors.toList()),
          PageRequest
              .of(beerPage.getPageable().getPageNumber(),
                  beerPage.getPageable().getPageSize()),
          beerPage.getTotalElements());
    } else {
      beerPagedList = new BeerPagedList(beerPage
          .getContent()
          .stream()
          .map(beerMapper::beerToBeerDto)
          .collect(Collectors.toList()),
          PageRequest
              .of(beerPage.getPageable().getPageNumber(),
                  beerPage.getPageable().getPageSize()),
          beerPage.getTotalElements());
    }

    return beerPagedList;
  }
}
