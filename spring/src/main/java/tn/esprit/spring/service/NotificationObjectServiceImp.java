package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.NotificationObject;
import tn.esprit.spring.repository.NotificationObjectRepository;

@Service
public class NotificationObjectServiceImp implements INotificationObjectService{
	@Autowired
	NotificationObjectRepository NotificationObjectRepository;

	@Override
	public List<NotificationObject> retrieveAllNotificationObjects() {
		List<NotificationObject> NotificationObjects = (List<NotificationObject>) NotificationObjectRepository.findAll();
		return NotificationObjects;
	}

	@Override
	public NotificationObject addNotificationObject(NotificationObject no) {
		NotificationObjectRepository.save(no);
		 return no;
	}

	@Override
	public void deleteNotificationObject(Long id) {
		NotificationObjectRepository.delete(NotificationObjectRepository.findById(id).get());
	}

	@Override
	public NotificationObject updateNotificationObject(NotificationObject no) {
		return NotificationObjectRepository.save(no);
	}
}
