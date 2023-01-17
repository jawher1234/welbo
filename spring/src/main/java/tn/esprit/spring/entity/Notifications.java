package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@SuppressWarnings("serial")

@Table( name = "Notifications")
public class Notifications implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; // Clé primaire
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NotificationObjectId", referencedColumnName = "id")
    private NotificationObject NotificationObject;

	private Long UserId;
	private Boolean Status;
	
	public Notifications(){}
	
	public Notifications(Long UserId, NotificationObject NotificationObject) {
	    this.UserId = UserId;
	    this.Status = false;
	    this.NotificationObject = NotificationObject;
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getUserId() {return UserId;}
	public void setUserId(Long userId) {UserId = userId;}
	public Boolean getStatus() {return Status;}
	public void setStatus(Boolean status) {Status = status;}
	public NotificationObject getNotificationObject() {return NotificationObject;}
	public void setNotificationObject(NotificationObject notificationObject) {NotificationObject = notificationObject;}
}