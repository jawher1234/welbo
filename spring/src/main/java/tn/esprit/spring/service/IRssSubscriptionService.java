package tn.esprit.spring.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRssSubscriptionService {
    public ResponseEntity<Object> subscribe(Long userId, Long providerId);
    public ResponseEntity<Object> unsubscribe(Long userId, Long providerId);
    public List feedFromLink (String link);
    public ResponseEntity<Object> rssByUser(Long userId);
}
