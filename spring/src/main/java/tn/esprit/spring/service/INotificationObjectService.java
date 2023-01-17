package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.NotificationObject;

public interface INotificationObjectService {
	List<NotificationObject> retrieveAllNotificationObjects();
	NotificationObject  addNotificationObject(NotificationObject no);
	void deleteNotificationObject(Long id);
	NotificationObject updateNotificationObject(NotificationObject no);
}
