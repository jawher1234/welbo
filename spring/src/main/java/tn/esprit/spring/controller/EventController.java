package tn.esprit.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.service.EventService;




@Controller
public class EventController {

	@Value("{file.upoad-dir")
	String FILE_DIRECTORY;
	@Autowired
	EventService eventService;
	
	@GetMapping("/events")
	@ResponseBody
	public List<Event> getAllEvents(){
		List<Event> list = eventService.getAllEvents();
		return list ;
		
		
	}
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@PostMapping(value="/Event/saveEvent")
	public @ResponseBody ResponseEntity<?> createEvent(@RequestParam("name") String name, @RequestParam("description") String description,@RequestParam("categorie") String categorie,Model model, HttpServletRequest request
			,final @RequestParam("image") MultipartFile file) {
		try {
			//String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(FILE_DIRECTORY);
			log.info("uploadDirectory:C:\\storage ");
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get("C:\\Users\\jawhe\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\PIDEV-4eme-Bien-etre-au-travail-\\src\\main\\resources\\image", fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
			String[] names = name.split(",");
			String[] descriptions = description.split(",");
			String[] categories = categorie.split(",");
			Date createDate = new Date();
			
			log.info("name: " + names[0]+" "+filePath);
			log.info("description: " + descriptions[0]);
			log.info("categorie: " + categories[0]);
		
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			Event event = new Event();
			event.setName(names[0]);
			event.setImage(imageData);;
			event.setDescription(descriptions[0]);
			event.setCategorie(categories[0]);
			event.setTime(createDate);
			
	
			eventService.saveEvent(event);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Event Saved With Image - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	
	@PostMapping("/AjouterEvent")
	@ResponseBody
	public Event AjouterProduit(@RequestBody Event e) {
		return eventService.AjouterEvent(e);
	}
	
	//   @ResponseBody
	 //   public void UpdateEvent(@RequestBody Event e ) {
		//	eventService.UpdateEvent(e);
		
	//	}
	@PutMapping("/modify-Event")
	@ResponseBody
	public Event modifyProduit(@RequestBody Event e) {
	return eventService.updateEvent(e);
	}
	
	@DeleteMapping("deleteEvent/{id}")
	@ResponseBody
	public void deleteEvent(@PathVariable("id") Long idEvent) {
		eventService.deleteEventById(idEvent);
		
		
		
	}

}
