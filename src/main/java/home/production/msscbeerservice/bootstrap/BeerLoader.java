package home.production.msscbeerservice.bootstrap;

import home.production.msscbeerservice.domain.Beer;
import home.production.msscbeerservice.repositories.BeerRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

  private final BeerRepositories beerRepositories;

  public BeerLoader(BeerRepositories beerRepositories) {
    this.beerRepositories = beerRepositories;
  }

  @Override
  public void run(String... args) throws Exception {
    loadBeerObjects();
  }

  private void loadBeerObjects() {
    if (beerRepositories.count() == 0) {

      beerRepositories.save(Beer.builder()
          .beerName("Mango Bobs")
          .beerStyle("IPA")
          .quantityToBrew(200)
          .minOnHand(12)
          .upc(337010000001L)
          .price(new BigDecimal("12.95"))
          .build());

      beerRepositories.save(Beer.builder()
          .beerName("Galaxy Cat")
          .beerStyle("PALE_ALE")
          .quantityToBrew(200)
          .minOnHand(12)
          .upc(337010000002L)
          .price(new BigDecimal("11.95"))
          .build());
    }
  }
}
