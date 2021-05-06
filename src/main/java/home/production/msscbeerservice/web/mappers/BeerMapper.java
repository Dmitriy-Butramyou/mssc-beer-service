package home.production.msscbeerservice.web.mappers;

import home.production.msscbeerservice.domain.Beer;
import home.production.msscbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
  BeerDto beerToBeerDto(Beer beer);

  Beer beerDtoToBeer(BeerDto dto);
}
