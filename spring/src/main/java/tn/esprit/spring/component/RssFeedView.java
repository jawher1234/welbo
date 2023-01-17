package tn.esprit.spring.component;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;
import tn.esprit.spring.entity.NewsfeedPost;
import tn.esprit.spring.service.INewsFeedPostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("postRssFeedView")
public class RssFeedView extends AbstractRssFeedView {

    private static final String CHANNEL_TITLE = "";
    private static final String CHANNEL_DESCRIPTION = "";
    private final INewsFeedPostService newsfeedService;

   // @Value("${application.base-url}")
 //   private String baseUrl;

    @Autowired
    public RssFeedView(INewsFeedPostService newsfeedService) {
        this.newsfeedService = newsfeedService;
    }

    @Override
    protected Channel newFeed() {
        Channel channel = new Channel("rss_2.0");
        channel.setLink("baseUrl" + "/feed/");
        channel.setTitle(CHANNEL_TITLE);
        channel.setDescription(CHANNEL_DESCRIPTION);
         channel.setPubDate(Date.from(newsfeedService.getLastPost().getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()));
        return channel;
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception {
        return newsfeedService.getLastPosts().stream()
                .map(this::createItem)
                .collect(Collectors.toList());
    }

    private Item createItem(NewsfeedPost newsfeedPost) {
        Item item = new Item();
        item.setLink("http://localhost:8083/PIDEV/api/newsfeed/" + newsfeedPost.getId());
        //item.setTitle(newsfeedPost.getContent());
        item.setDescription(createDescription(newsfeedPost));
        item.setPubDate(Date.from(newsfeedPost.getModifiedAt().atZone(ZoneId.systemDefault()).toInstant()));
        return item;
    }

    private Description createDescription(NewsfeedPost newsfeedPost) {
        Description description = new Description();
        description.setType(Content.HTML);
        description.setValue(newsfeedPost.getContent());
        return description;
    }

}
