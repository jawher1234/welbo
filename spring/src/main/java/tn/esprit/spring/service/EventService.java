package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Event;


public interface EventService {
	Event AjouterEvent(Event e);
	void saveEvent(Event event);
	 Event updateEvent(Event e);
	 void deleteEvent(Event e);
	 void deleteEventById(Long id);
	 Event getEvent(Long id);
	 List<Event> getAllEvents();

}
