package home.production.msscbeerservice.events;

import home.production.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

  static final long serialVersionUID = -4844104841230020484L;

  private final BeerDto beerDto;
}
