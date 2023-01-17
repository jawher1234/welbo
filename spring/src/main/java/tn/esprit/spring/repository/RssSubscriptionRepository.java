package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.RssFeedProvider;
import tn.esprit.spring.entity.RssSubscription;

import java.util.List;

@Repository
public interface RssSubscriptionRepository extends CrudRepository<RssSubscription,Long> {
    RssSubscription findRssSubscriptionByProviderAndSubscriber(RssFeedProvider provider, User subscriber);
    Boolean existsRssSubscriptionBySubscriberAndProvider(User subscriber, RssFeedProvider provider);
    List<RssSubscription> findRssSubscriptionBySubscriber(User subscriber);
}
