package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import tn.esprit.spring.entity.RssFeedProvider;
import tn.esprit.spring.service.IRssProviderService;
import tn.esprit.spring.service.IRssSubscriptionService;
import tn.esprit.spring.component.RssFeedView;

@RestController
@RequestMapping(path ="/api/RSS")
public class RssFeedController {
    @Autowired
    private RssFeedView view;
    @Autowired
    IRssProviderService iRssProviderService;
    @Autowired
    IRssSubscriptionService iRssSubscriptionService;

    @RequestMapping(value = "", produces = "application/*")
    public View getFeed() {
        return view;
    }
//RSS

    @GetMapping("/for/{userId}")
    public ResponseEntity<Object> rssByUser(@PathVariable Long userId){
       return iRssSubscriptionService.rssByUser(userId);
    }
//RSS provider crud

    @GetMapping("/providers")
    public ResponseEntity<Object> getNewsFeedPosts(){
        return iRssProviderService.getRssFeedProviders();
    }

    @GetMapping({"/provider/{id}"})
    public ResponseEntity<Object> getNewsFeedPost(@PathVariable Long id){
        return iRssProviderService.getRssFeedProvider(id);
    }
    @PostMapping
    @RequestMapping(path = "/provider/new")
    @ResponseBody
    public ResponseEntity<Object> addProvider(@RequestBody RssFeedProvider rssFeedProvider){

        return iRssProviderService.ajouterRssFeedProvider(rssFeedProvider);
    }
    @DeleteMapping({"/provider/delete/{id}"})
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return iRssProviderService.deleteRssFeedProvider(id);
    }
    @PutMapping
    @RequestMapping(path = "/provider/edit")
    @ResponseBody
    public ResponseEntity<Object> edit(@RequestBody RssFeedProvider rssFeedProvider){
        return iRssProviderService.modifierRssFeedProvider(rssFeedProvider);
    }
    //RSS subscription
    @PostMapping
    @RequestMapping(path = "/subscribe/{subscriberId}/{providerId}")
    @ResponseBody
    public ResponseEntity<Object> addSubscription(@PathVariable(value = "subscriberId") Long subscriber, @PathVariable(value = "providerId") Long providerId){

            return iRssSubscriptionService.subscribe(subscriber,providerId);

    }
    @PostMapping
    @RequestMapping(path = "/unsubscribe/{subscriberId}/{providerId}")
    @ResponseBody
    public ResponseEntity<Object> deleteSubscription(@PathVariable(value = "subscriberId") Long subscriber, @PathVariable(value = "providerId") Long providerId){

        return iRssSubscriptionService.unsubscribe(subscriber,providerId);

    }

}
