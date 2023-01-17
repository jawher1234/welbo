package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer,Long> {
    List<Offer> findAllByState(Boolean state);
}
