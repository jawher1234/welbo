package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Likes;
import tn.esprit.spring.entity.NotificationObject;
import tn.esprit.spring.entity.Notifications;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.LikesRepository;

@Service
public class LikesServiceImp implements ILikesService{
	@Autowired
	LikesRepository LikesRepository;

	@Override
	public List<Likes> retrieveAllLikes() {
		List<Likes> Likes = (List<Likes>) LikesRepository.findAll();
		return Likes;
	}

	@Override
	public Likes addLike(Likes l) {
		LikesRepository.save(l);
		 return l;
	}

	@Override
	public void deleteLike(Long id) {
		LikesRepository.delete(LikesRepository.findById(id).get());
	}

	@Override
	public Likes updateLike(Likes l) {
		return LikesRepository.save(l);
	}

	@Override
	public void addobjnotif(NotificationObject no) {
		LikesRepository.insertnotifobj(no.getForumPostId(), no.getNewsFeedPostId(), no.getType(), no.getCreationDate());
	}

	@Override
	public void addNotification(Notifications n) {
		LikesRepository.insertnotification(n.getNotificationObject().getId(), n.getStatus(), n.getUserId());
	}

	@Override
	public Long findlastobj() {
		Long n = LikesRepository.retrieveLastNotificationObjectId();
		return n;
	}

	@Override
	public Long retrieveForumPostUserId(Long ForumPostId) {
		//YourEntityClassRepositorie.findById(id).get();
		Long userid = LikesRepository.retrieveForumPostUserId(ForumPostId);
		return userid;
	}

	/*
	@Override
	public Long retrieveNewsFeedPostUserId(Long NewsFeedPostId) {
		Long userid = LikesRepository.retrieveNewsFeedPostUserId(NewsFeedPostId);
		return userid;
	}
	*/
	
	
	@Override
	public User retrieveUserFromUserId(Long userid) {
		User user = LikesRepository.retrieveUserFromUserId(userid);
		return user;
	}
	
}
