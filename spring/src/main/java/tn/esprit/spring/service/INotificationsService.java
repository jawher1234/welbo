package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Notifications;

public interface INotificationsService {
	List<Notifications> retrieveAllNotifications();
	Notifications  addNotification(Notifications n);
	void deleteNotification(Long id);
	Notifications updateNotification(Notifications n);
}
