package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Payload.response.UserResponse;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.Payload.response.MessageResponse;
import tn.esprit.spring.repository.IUserRepository;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IUserRepository userRepository;

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity< ? > deleteClient(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse(" Deleted successfully!"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(" Failed to delete employee!"));
        }
    }

    @GetMapping("/profile/{userId}")
    public UserResponse getUser(@PathVariable("userId") Long id) {

        User user = userRepository.findById(id).get();
        UserResponse userResponse = new UserResponse(user.getId(),
                user.getUserName(),
                user.getName(),
                user.getLastName(),
                user.getNid(),
                user.getEmail(),
                user.getPassword(),
                user.isBlocked(),
                user.getJobTitle(),
                (Timestamp) user.getBirthDate(),
                user.getCellPhoneNumber(),
                user.getHomePhoneNumber(),
                user.getProfilePicture(),
                user.getVerificationCode(),
                user.getRoles(),
                user.getDepartement(),
                user.getAddress(),
                user.getCity(),
                user.getCountry(),
                user.getPostalCode(),
                user.getAboutMe());
        return userResponse;
    }

    @PostMapping("/blockEmployee/{id}")
    public ResponseEntity< ? > banEmployee(@PathVariable Long id) {
        try {
            User user =userRepository.findById(id).get();
            user.setBlocked(true);
            userRepository.save(user);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse(" blocked successfully!"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(" Failed to block employee!"));
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @GetMapping("/NonBlockedUsers")
    public ResponseEntity<List<User>> getUsersNotBlocked(){
        List<User> users = (List<User>) userRepository.findUsersByBlockedTrue();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @GetMapping("/BlockedUsers")
    public ResponseEntity<List<User>> getUsersBlocked(){
        List<User> users = (List<User>) userRepository.findUsersByBlockedFalse();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

}
