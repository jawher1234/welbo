package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Notifications;
import tn.esprit.spring.repository.NotificationsRepository;

@Service
public class NotificationsServiceImp implements INotificationsService{
	@Autowired
	NotificationsRepository NotificationsRepository;

	@Override
	public List<Notifications> retrieveAllNotifications() {
		List<Notifications> Notifications = (List<Notifications>) NotificationsRepository.findAll();
		return Notifications;
	}

	@Override
	public Notifications addNotification(Notifications n) {
		NotificationsRepository.save(n);
		 return n;
	}

	@Override
	public void deleteNotification(Long id) {
		NotificationsRepository.delete(NotificationsRepository.findById(id).get());
	}

	@Override
	public Notifications updateNotification(Notifications n) {
		return NotificationsRepository.save(n);
	}
}
