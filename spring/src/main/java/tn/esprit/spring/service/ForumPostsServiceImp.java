package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.ForumPosts;
import tn.esprit.spring.entity.Likes;
import tn.esprit.spring.repository.ForumPostsRepository;

@Service
public class ForumPostsServiceImp implements IForumPostsService{
	@Autowired
	ForumPostsRepository ForumPostsRepository;

	@Override
	public List<ForumPosts> retrieveAllForumPosts() {
		List<ForumPosts> ForumPosts = (List<ForumPosts>) ForumPostsRepository.findAll();
		return ForumPosts;
	}

	@Override
	public ForumPosts addForumPost(ForumPosts fp) {
		ForumPostsRepository.save(fp);
		 return fp;
	}

	@Override
	public void deleteForumPost(Long id) {
		ForumPostsRepository.delete(ForumPostsRepository.findById(id).get());
	}

	@Override
	public ForumPosts updateForumPost(ForumPosts fp) {
		return ForumPostsRepository.save(fp);
	}

	@Override
	public List<ForumPosts> retrievePostByCateg(Long idcateg) {
		List<ForumPosts> ForumPosts = ForumPostsRepository.retrievePostsByCategorie(idcateg);
		return ForumPosts;
	}

	@Override
	public List<Likes> retrievePostLikes(Long forumpostid) {
		List<Likes> PostLikes = ForumPostsRepository.retrievePostLikes(forumpostid);
		return PostLikes;
	}

	@Override
	public ForumPosts retreiveForumPostById(Long forumpostid) {
		return ForumPostsRepository.findById(forumpostid).get();
	}

}
