package home.production.msscbeerservice.services.inventory;

import home.production.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFailover implements InventoryFailoverFeignClient {

    private final InventoryFailoverFeignClient failoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory() {
        return failoverFeignClient.getOnHandInventory();
    }
}
