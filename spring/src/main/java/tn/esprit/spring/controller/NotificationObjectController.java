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

import tn.esprit.spring.entity.NotificationObject;
import tn.esprit.spring.service.INotificationObjectService;

@RestController
@RequestMapping("/NotificationObject")
public class NotificationObjectController {
	@Autowired
	INotificationObjectService NotificationObjectService;
	
	//http://localhost:8083/PIDEV/NotificationObject/retrieve-all-NotificationObjects
	@GetMapping("/retrieve-all-NotificationObjects")
	public List<NotificationObject> getNotificationObjects() {
	List<NotificationObject> listNotificationObjects = NotificationObjectService.retrieveAllNotificationObjects();
	return listNotificationObjects;
	}
	
	//http://localhost:8083/PIDEV/NotificationObject/add-NotificationObject
	@PostMapping("/add-NotificationObject")
	@ResponseBody
	public NotificationObject addNotificationObject(@RequestBody NotificationObject c)
	{
		NotificationObject NotificationObject = NotificationObjectService.addNotificationObject(c);
		return NotificationObject;
	}
	
	//http://localhost:8083/PIDEV/NotificationObject/remove-NotificationObject/{id}
	@DeleteMapping("/remove-NotificationObject/{id}")
	@ResponseBody
	public void removeNotificationObject(@PathVariable("id") Long id) {
		NotificationObjectService.deleteNotificationObject(id);
	}
	
	//http://localhost:8083/PIDEV/NotificationObject/modify-NotificationObject
	@PutMapping("/modify-NotificationObject")
	@ResponseBody
	public NotificationObject modifyNotificationObject(@RequestBody NotificationObject NotificationObject) {
	return NotificationObjectService.updateNotificationObject(NotificationObject);
	}
}
