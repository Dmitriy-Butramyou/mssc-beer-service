package home.production.msscbeerservice.services.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled //utility for manual testing
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

  @Autowired
  BeerInventoryService beerInventoryService;

  @BeforeEach
  void setUp() {
  }

  @Test
  void getOnHandInventory() {
    //Integer goh = beerInventoryService.getOnHandInventory(BeerLoader.BEER_1_UUID);

   //System.out.println(goh);
  }
}