package tn.esprit.spring.service;

import org.springframework.http.ResponseEntity;

public interface IOfferReservationService {
    public ResponseEntity<Object> addReservation(Long userId, Long offerId);
    public ResponseEntity<Object> removeReservation(Long reservationId);
    public ResponseEntity<Object> validateReservation(Long reservationId);
    public ResponseEntity<Object> findAllReservations();
    public ResponseEntity<Object> findReservationsByUser(Long userId);
    public ResponseEntity<Object> findReservationsByOffer(Long offerId);
    public ResponseEntity<Object> findReservationsById(Long reservationId);


}
