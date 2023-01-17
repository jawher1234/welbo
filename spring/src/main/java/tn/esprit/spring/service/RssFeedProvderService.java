package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.RssFeedProvider;
import tn.esprit.spring.repository.RssProviderRepository;
import tn.esprit.spring.response.ResponseHandler;

import java.util.List;

@Service
public class RssFeedProvderService implements IRssProviderService{
    @Autowired
    RssProviderRepository rssProviderRepository;

    @Override
    public ResponseEntity<Object> ajouterRssFeedProvider(RssFeedProvider rssfeedprovider) {
        RssFeedProvider addedpost = new RssFeedProvider();
            try{
                addedpost = rssProviderRepository.save(rssfeedprovider);
                return ResponseHandler.generateResponse("Successfully added rss provider!", HttpStatus.OK, addedpost);
            }catch (Exception e){
                return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
            }

    }

    @Override
    public ResponseEntity<Object> deleteRssFeedProvider(Long rssfeedproviderId) {
        try{
            rssProviderRepository.deleteById(rssfeedproviderId);
            return ResponseHandler.generateResponse("provider successfully deleted !", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @Override
    public ResponseEntity<Object> getRssFeedProviders() {
        try{
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, (List<RssFeedProvider>) rssProviderRepository.findAll());
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }



    @Override
    public ResponseEntity<Object> modifierRssFeedProvider(RssFeedProvider rssfeedprovider) {

        try{

            return ResponseHandler.generateResponse("Successfully updated post", HttpStatus.OK, rssProviderRepository.save(rssfeedprovider));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
        //return rssfeedprovider;
    }

    @Override
    public ResponseEntity<Object> getRssFeedProvider(Long rssfeedproviderId) {
        try{
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, rssProviderRepository.findById(rssfeedproviderId).get());
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
