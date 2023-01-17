package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.spring.entity.Badges;
import tn.esprit.spring.service.BadgesServiceImpl;
import tn.esprit.spring.upload.ResponseFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/badge")
public class BadgeRestController {
	@Autowired
	BadgesServiceImpl badgesServiceImpl;
	//@Autowired
	  //private BadgesService badgesService;
	@Value("{file.upload-dir")
	String FILE_DIRECTORY;

	@PostMapping("/add-Badge")
	@ResponseBody
	public Badges addBadges(@RequestBody Badges b)
	{
	Badges badges = badgesServiceImpl.addbadge(b);
	return badges;
	}
	
	@GetMapping("/retrieve-All-Badges")
	@ResponseBody
	public List<Badges> getBadges() {
		List<Badges> listBadges = badgesServiceImpl.retrieveAllBadges();
		return listBadges;
		}
	@PutMapping("/modify-Badges")
	@ResponseBody
	public Badges modifyBadges(@RequestBody Badges badge) {
	return badgesServiceImpl.updateBadges(badge);
	}
	@DeleteMapping("/remove-Badges/{id}")
	@ResponseBody
	public void removeBadges(@PathVariable("id")Integer Id)
	{
		badgesServiceImpl.deleteBadges(Id);
	}
	
	//http://localhost:8083/PIDEV/badge/add-image
		@PostMapping("/add-image")
		public @ResponseBody ResponseEntity<?> createbadge(@RequestParam("nom") String nom , @RequestParam("fileName") String fileName,@RequestParam("description") String description,Model model, HttpServletRequest request
				,final @RequestParam("Data") MultipartFile file) {
			try {
				String uploadDirectory = request.getServletContext().getRealPath(FILE_DIRECTORY);
				String Namefile = file.getOriginalFilename();
				String filePath = Paths.get("C:\\Users\\trabe\\OneDrive\\Bureau\\projet PI\\Git\\PIDEV-4eme-Bien-etre-au-travail-\\src\\main\\resources\\image", Namefile).toString();
				if (Namefile == null || Namefile.contains("..")) {
					model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
					return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + Namefile, HttpStatus.BAD_REQUEST);
				}
			
				try {
					File dir = new File(uploadDirectory);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// Save the file locally
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
					stream.write(file.getBytes());
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				byte[] imageData = file.getBytes();
				Badges badges = new Badges();
				badges.setNom(nom);
				badges.setFileName(fileName);
				badges.setDescription(description);
				
				badgesServiceImpl.addbadge(badges);
				return new ResponseEntity<>("Badge uploaded with success" + Namefile, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
//    @PostMapping("/uploadFile")
//	  public ResponseEntity<ResponseMessage> uploadFile (@RequestParam("file") MultipartFile file) {
//	    String message = "";
//	    try {
//	     badgesServiceImpl.store(file);
//	      message = "Uploaded the file successfully: " + file.getOriginalFilename() + "!";
//	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//	    }catch (Exception e) {
//	     message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//	     return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//	    }
//	 }
	 @GetMapping("/files")
	 public ResponseEntity<List<ResponseFile>> getListFiles() {
	   List<ResponseFile> files = badgesServiceImpl.getAllFiles().map(badges -> {
	     String fileDownloadUri = ServletUriComponentsBuilder
	         .fromCurrentContextPath()
	          .path("/files/")
	          .path(badges.getNom())
	          .toUriString();
	     return new ResponseFile(
	    		 badges.getFileName(), 
	    		 fileDownloadUri,
	      badges.getDescription(),
	      badges.getData().length);
	   }).collect(Collectors.toList());
	  return ResponseEntity.status(HttpStatus.OK).body(files);
	 }
	  @GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
	    Badges badges = badgesServiceImpl.getFile(id);
	   return ResponseEntity.ok()
	       .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; nameFile=\"" + badges.getFileName() + "\"")
	       .body(badges.getData());
	  }
    @GetMapping("/getAllBadgesBynom")
	public List<String> getAllBadgesBynom()
			{
		return badgesServiceImpl.getAllBadgesBynom();
			}
}
	


