package tn.esprit.spring.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Offer;

public interface IOfferService {
    public ResponseEntity<Object> ajouterOffer(Offer offer);
    public ResponseEntity<Object> deleteOffer(Long offerId);
    public ResponseEntity<Object> getOffers();
    public ResponseEntity<Object> modifierOffer(Offer offer);
    public ResponseEntity<Object> getOffer(Long offerId);
    public ResponseEntity<Object> updateOfferState(Long offerId);
    public ResponseEntity<Object> getOffersByState(boolean state);
    public void uploadFile(MultipartFile file);
    public void createExcel(String saveToPath);
    public void dataFromExcel(String fileLocation);
}
