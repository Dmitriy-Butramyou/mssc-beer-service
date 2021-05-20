package home.production.msscbeerservice.events;

import home.production.msscbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

  static final long serialVersionUID = -4844104841230020484L;

  private BeerDto beerDto;
}
