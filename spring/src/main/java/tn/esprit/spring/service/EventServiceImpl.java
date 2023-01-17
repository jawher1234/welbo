package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.repository.EventRepository;



@Service
@Transactional
public class EventServiceImpl implements EventService {
	@Autowired
	EventRepository eventRepository ;
	@Override
	public void saveEvent(Event event) {
		eventRepository.save(event);	
	}
	@Override
	public Event AjouterEvent(Event e) {
		eventRepository.save(e);
		return e;
	}

	@Override
	public Event updateEvent(Event e) {
		
		return eventRepository.save(e);
		}

	


	@Override
	public void deleteEvent(Event e) {
		eventRepository.delete(e);
		
	}

	@Override
	public void deleteEventById(Long id) {
	   eventRepository.deleteById(id);
		
	}

	@Override
	public Event getEvent(Long id) {
		
		return eventRepository.findById(id).get();
	}

	@Override
	public List<Event> getAllEvents() {
		
		return  (List<Event>) eventRepository.findAll();
	}

}
