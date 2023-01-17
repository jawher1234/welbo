package tn.esprit.spring.service;

import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.BsUserRepository;
import tn.esprit.spring.repository.RssProviderRepository;
import tn.esprit.spring.repository.RssSubscriptionRepository;
import tn.esprit.spring.response.ResponseHandler;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssSubscriptionService implements IRssSubscriptionService{
    @Autowired
    RssSubscriptionRepository rssSubscriptionRepository;
    @Autowired
    BsUserRepository bsUserRepository;
    @Autowired
    RssProviderRepository rssProviderRepository;
    @Override
    public ResponseEntity<Object> subscribe(Long userId, Long providerId) {
        try{
            RssSubscription subscription= new RssSubscription();
            User subscriber = bsUserRepository.findById(userId).get();
            RssFeedProvider provider = rssProviderRepository.findById(providerId).get();
            if(rssProviderRepository.findById(providerId).isEmpty()){
                return ResponseHandler.generateResponse("provider inexistant!", HttpStatus.MULTI_STATUS, null);
            }
            if(bsUserRepository.findById(userId).isEmpty()){
                return ResponseHandler.generateResponse("user inexistant!", HttpStatus.MULTI_STATUS, null);
            }
            if (rssSubscriptionRepository.existsRssSubscriptionBySubscriberAndProvider(subscriber,provider)){
                return ResponseHandler.generateResponse("subscription existant!", HttpStatus.MULTI_STATUS, null);
            }



            subscription.setSubscriber(subscriber);
            subscription.setProvider(provider);
            subscription.setCreatedAt(LocalDateTime.now());
            rssSubscriptionRepository.save(subscription);
            return ResponseHandler.generateResponse("Successfully subscribed!", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @Override
    public ResponseEntity<Object> unsubscribe(Long userId, Long providerId) {
        try{
            RssSubscription subscription= new RssSubscription();
            User subscriber = bsUserRepository.findById(userId).get();
            RssFeedProvider provider = rssProviderRepository.findById(providerId).get();
            if(rssProviderRepository.findById(providerId).isEmpty()){
                return ResponseHandler.generateResponse("provider inexistant!", HttpStatus.MULTI_STATUS, null);
            }
            if(bsUserRepository.findById(userId).isEmpty()){
                return ResponseHandler.generateResponse("user inexistant!", HttpStatus.MULTI_STATUS, null);
            }
            subscription = rssSubscriptionRepository.findRssSubscriptionByProviderAndSubscriber(provider,subscriber);

            rssSubscriptionRepository.delete(subscription);
            return ResponseHandler.generateResponse("Successfully unsubscribed !", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
    public List feedFromLink (String link){


        boolean ok = false;
        List<Rss> list = new ArrayList<>();

        if (link.length()>0) {
            try {
                URL feedUrl = new URL(link);

                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));

                List res = feed.getEntries();
                for(Object o : res) {
                    Rss postFromXml = new Rss();
                    //System.out.println(((SyndEntryImpl) o).getDescription().getValue());
                    String content = ((SyndEntryImpl) o).getDescription().getValue();
                    System.out.println(content);
                    postFromXml.setDescription(content);
                    postFromXml.setLink(((SyndEntryImpl) o).getLink());
                    postFromXml.setCreatedAt(((SyndEntryImpl) o).getPublishedDate());
                    list.add(postFromXml);
                }

                ok = true;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ERROR: "+ex.getMessage());
            }
        }


        return list;
    }

    @Override
    public ResponseEntity<Object> rssByUser(Long userId) {
        List<RssFeedProvider> providers = new ArrayList<>();
          List mylist = new ArrayList<>();
        User subscriber = bsUserRepository.findById(userId).get();
        List<RssSubscription> subscriptions = rssSubscriptionRepository.findRssSubscriptionBySubscriber(subscriber);
        for (RssSubscription rssSubscription : subscriptions){
            providers.add(rssSubscription.getProvider());
        }
        for(RssFeedProvider o : providers){
            mylist.add(feedFromLink (o.getLink()));
        }
        return ResponseHandler.generateResponse("fffff", HttpStatus.MULTI_STATUS, mylist);    }

}
