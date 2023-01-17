package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Payload.response.MessageResponse;
import tn.esprit.spring.entity.Departement;
import tn.esprit.spring.repository.IDepratmentRepository;

import java.util.List;

@RestController
@RequestMapping("/departement")
public class DepartementController {
    @Autowired
    IDepratmentRepository depratmentRepository;

    @PostMapping("/add-departement")
    @ResponseBody
    public ResponseEntity<?> addForumPost(@RequestBody Departement departementbody)
    {
        Departement departement = new Departement();
        departement.setName(departementbody.getName());
        departement.setDescription(departementbody.getDescription());
        depratmentRepository.save(departement);
        return ResponseEntity.ok(new MessageResponse("Department added successfully!"));
    }
    @DeleteMapping("/remove-departement/{id}")
    @ResponseBody
    public ResponseEntity<?> removeForumPost(@PathVariable("id") Long id) {
        depratmentRepository.delete(depratmentRepository.findById(id).get());
        return ResponseEntity.ok(new MessageResponse("Department deleted successfully!"));
    }
    @GetMapping("/retrieve-all-departement")
    public List<Departement> getAllDepartement() {
        List<Departement> departements= (List<Departement>) depratmentRepository.findAll();
        return departements;
    }
    @GetMapping("/retrievedepartementbyid/{id}")
    public Departement getDepartementById(@PathVariable("id") Long id) {
        Departement departement=  depratmentRepository.findById(id).get();
        return departement;
    }


}
