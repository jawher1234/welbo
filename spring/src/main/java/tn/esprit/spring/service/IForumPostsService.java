package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.ForumPosts;
import tn.esprit.spring.entity.Likes;

public interface IForumPostsService {
	List<ForumPosts> retrieveAllForumPosts();
	ForumPosts  addForumPost(ForumPosts fp);
	void deleteForumPost(Long id);
	ForumPosts updateForumPost(ForumPosts fp);
	List<ForumPosts> retrievePostByCateg(Long idcateg);
	List<Likes> retrievePostLikes(Long forumpostid);
	ForumPosts retreiveForumPostById(Long forumpostid);
}