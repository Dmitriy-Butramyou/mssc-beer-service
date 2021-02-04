package home.production.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BeerDto {

  private UUID id;
  protected Integer version;

  private OffsetDateTime createdDate;
  private OffsetDateTime lastModifiedDate;

  private String beerName;
  private BeerStyleEnum beerStyle;

  private Long upc;

  private BigDecimal price;
}
