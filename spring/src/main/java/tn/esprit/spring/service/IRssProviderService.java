package tn.esprit.spring.service;

import org.springframework.http.ResponseEntity;
import tn.esprit.spring.entity.RssFeedProvider;

public interface IRssProviderService {
    public ResponseEntity<Object> ajouterRssFeedProvider(RssFeedProvider rssprovider);
    public ResponseEntity<Object> deleteRssFeedProvider(Long rssproviderId);
    public ResponseEntity<Object> getRssFeedProviders();
    public ResponseEntity<Object> modifierRssFeedProvider(RssFeedProvider rssprovider);
    public ResponseEntity<Object> getRssFeedProvider(Long rssproviderId);
}
