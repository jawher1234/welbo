package tn.esprit.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.MembersOfCompany;
import tn.esprit.spring.Payload.response.MessageResponse;
import tn.esprit.spring.repository.IMembresOfCompany;
import tn.esprit.spring.service.MOCService;
import tn.esprit.spring.helper.CSVHelper;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/admin")
public class CSVController {

  @Autowired
  MOCService fileService;

  @PostMapping("/csv/upload")
  public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
    String message;

    if (CSVHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(message));
  }

  @GetMapping("/csv/MOCs")
  public ResponseEntity<List<MembersOfCompany>> getAllMembersOfCompanys() {
    try {
      List<MembersOfCompany> tutorials = fileService.getAll();

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/csv/download")
  public ResponseEntity<Resource> getFile() {
    String filename = "membres of company.csv";
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
        .contentType(MediaType.parseMediaType("text/csv"))
        .body(file);
  }
  @Autowired
  IMembresOfCompany imoc;
  @DeleteMapping("/csv/delete")
  public ResponseEntity< ? > deleteAll(){
    imoc.deleteAll();
    return ResponseEntity
            .ok()
            .body(new MessageResponse(" Deleted successfully!"));
  }

}
