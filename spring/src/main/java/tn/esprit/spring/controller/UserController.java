package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.Payload.request.UserUpdateRequest;
import tn.esprit.spring.Payload.response.MessageResponse;
import tn.esprit.spring.Payload.response.UserResponse;
import tn.esprit.spring.repository.IDepratmentRepository;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.DepartementService;
import tn.esprit.spring.service.IDepartementService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    IDepratmentRepository depratmentRepository;
    @Autowired
    IDepartementService departementService = new DepartementService();
    @Value("{file.upload-dir")
    String FILE_DIRECTORY;

    @PutMapping("/updateuser")
    public ResponseEntity<?> registerClient(@RequestBody User body) {

        userRepository.save(body);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("Account is updated successfully"));
    }

    @PutMapping("/uploadprofilepic")
    public @ResponseBody ResponseEntity<?> createForumPost(@RequestParam("userId") Long UserId, Model model, HttpServletRequest request, final @RequestParam("profilepic") MultipartFile file) {
        try {
            //String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
            String uploadDirectory = request.getServletContext().getRealPath(FILE_DIRECTORY);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get("C:\\Users\\PC-Yassine\\Desktop\\PIDev\\Welbo\\PIDEV-4eme-Bien-etre-au-travail-\\src\\main\\resources\\image", fileName).toString();
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
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
            User user = userRepository.findById(UserId).get();
            user.setProfilePicture(imageData);
            userRepository.save(user);
            return new ResponseEntity<>("profilepic " + fileName+" uploded successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/profile/{userId}")
    public User getUser(@PathVariable("userId") Long id) {

        User user = userRepository.findById(id).get();
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

}
