package home.production.msscbeerservice.repositories;

import home.production.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepositories extends PagingAndSortingRepository<Beer, UUID> {
}
