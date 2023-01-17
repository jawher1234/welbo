package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.BsUserRepository;
import tn.esprit.spring.repository.OfferRepository;
import tn.esprit.spring.repository.OfferReservationRepository;
import tn.esprit.spring.response.ResponseHandler;

import java.time.LocalDateTime;

@Service
public class OfferReservationService implements IOfferReservationService{
    @Autowired
    BsUserRepository bsUserRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    OfferReservationRepository offerReservationRepository;

    @Override
    public ResponseEntity<Object> addReservation(Long userId, Long offerId) {
        try{
            OfferReservation offerReservation = new OfferReservation();
            User user = bsUserRepository.findById(userId).get();
            Offer offer = offerRepository.findById(offerId).get();
           if (user.getId() == null){
               return ResponseHandler.generateResponse("user inexistant!", HttpStatus.MULTI_STATUS, user);
           }
            if (offer.getId() == null){
                return ResponseHandler.generateResponse("offer inexistant!", HttpStatus.MULTI_STATUS, offer);
            }
            if (offer.getQuantity()==0){
                return ResponseHandler.generateResponse("Sorry quantity = 0!", HttpStatus.MULTI_STATUS, offer);
            }
            if (!offer.getState()){
                return ResponseHandler.generateResponse("Sorry offer is not available now!", HttpStatus.MULTI_STATUS, offer);
            }
            if (offerReservationRepository.existsAllByReservedByAndOffer(user,offer)){
                return ResponseHandler.generateResponse("You have already reserved this offer!", HttpStatus.MULTI_STATUS, offer);
            }
            offer.setQuantity(offer.getQuantity()-1);
            offerReservation.setOffer(offer);
            offerReservation.setReservedBy(user);
            offerReservation.setReservedAt(LocalDateTime.now());
            offerReservation.setState(false);
            offerRepository.save(offer);
            offerReservationRepository.save(offerReservation);

            return ResponseHandler.generateResponse("offer reserved successfully", HttpStatus.MULTI_STATUS, offerReservation);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @Override
    public ResponseEntity<Object> removeReservation(Long reservationId) {
        try{

              Offer offer = offerReservationRepository.findById(reservationId).get().getOffer();
              offer.setQuantity(offer.getQuantity()+1);
              offerRepository.save(offer);
            offerReservationRepository.deleteById(reservationId);
            return ResponseHandler.generateResponse("reservation "+offer.getId()+" is deleted successfully", HttpStatus.MULTI_STATUS, offer);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @Override
    public ResponseEntity<Object> validateReservation(Long reservationId) {
        try{
            OfferReservation reservation = offerReservationRepository.findById(reservationId).get();
            reservation.setState(!reservation.getState());
            reservation.setValidatedAt(LocalDateTime.now());
            offerReservationRepository.save(reservation);
            return ResponseHandler.generateResponse("reservation "+reservationId+" is validated successfully", HttpStatus.MULTI_STATUS, reservation);

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @Override
    public ResponseEntity<Object> findAllReservations() {
        try{

            return ResponseHandler.generateResponse("fetch reservations success", HttpStatus.MULTI_STATUS, offerReservationRepository.findAll());

        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @Override
    public ResponseEntity<Object> findReservationsByUser(Long userId) {
        try{
            User user = bsUserRepository.findById(userId).get();
            return ResponseHandler.generateResponse("fetch reservations success", HttpStatus.MULTI_STATUS, offerReservationRepository.findAllByReservedBy(user));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @Override
    public ResponseEntity<Object> findReservationsByOffer(Long offerId) {
        try{
            Offer offer = offerRepository.findById(offerId).get();
            return ResponseHandler.generateResponse("fetch reservations success", HttpStatus.MULTI_STATUS, offerReservationRepository.findAllByOffer(offer));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @Override
    public ResponseEntity<Object> findReservationsById(Long reservationId) {
        try{
            return ResponseHandler.generateResponse("fetch reservations success", HttpStatus.MULTI_STATUS, offerReservationRepository.findById(reservationId).get());
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
