package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.Offer;
import tn.esprit.spring.entity.OfferReservation;

import java.util.List;

@Repository
public interface OfferReservationRepository extends CrudRepository<OfferReservation,Long> {
    List<OfferReservation> findAllByReservedBy(User user);
    List<OfferReservation> findAllByOffer(Offer offer);
    Boolean existsAllByReservedByAndOffer(User user, Offer offer);
}
