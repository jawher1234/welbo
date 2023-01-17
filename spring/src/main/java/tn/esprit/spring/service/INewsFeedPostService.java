package tn.esprit.spring.service;

import org.springframework.http.ResponseEntity;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.NewsfeedPost;

import java.util.List;

public interface INewsFeedPostService {
    public ResponseEntity<Object> ajouterNewsfeedPost(NewsfeedPost newsfeedPost);
    public ResponseEntity<Object> deleteNewsfeedPost(Long newsfeedPostId);
    public ResponseEntity<Object> getNewsfeedPosts();
    public List<NewsfeedPost> getLastPosts();
    public NewsfeedPost getLastPost();
    public ResponseEntity<Object> modifierNewsfeedPost(NewsfeedPost newsfeedPost);
    public ResponseEntity<Object> getNewsfeedPost(Long newsfeedPostId);
    public ResponseEntity<Object> getPostsByPostedBy(User postedBy);

}
