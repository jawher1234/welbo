package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Notifications;
import tn.esprit.spring.service.INotificationsService;

@RestController
@RequestMapping("/Notification")
public class NotificationsController {
	@Autowired
	INotificationsService Notificationservice;
	
	//http://localhost:8083/PIDEV/Notification/retrieve-all-Notifications
	@GetMapping("/retrieve-all-Notifications")
	public List<Notifications> getNotifications() {
	List<Notifications> listNotifications = Notificationservice.retrieveAllNotifications();
	return listNotifications;
	}
	
	//http://localhost:8083/PIDEV/Notification/add-Notification
	@PostMapping("/add-Notification")
	@ResponseBody
	public Notifications addNotification(@RequestBody Notifications c)
	{
		Notifications Notification = Notificationservice.addNotification(c);
		return Notification;
	}
	
	//http://localhost:8083/PIDEV/Notification/remove-Notification/{id}
	@DeleteMapping("/remove-Notification/{id}")
	@ResponseBody
	public void removeNotification(@PathVariable("id") Long id) {
		Notificationservice.deleteNotification(id);
	}
	
	//http://localhost:8083/PIDEV/Notification/modify-Notification
	@PutMapping("/modify-Notification")
	@ResponseBody
	public Notifications modifyNotification(@RequestBody Notifications Notification) {
	return Notificationservice.updateNotification(Notification);
	}
}
