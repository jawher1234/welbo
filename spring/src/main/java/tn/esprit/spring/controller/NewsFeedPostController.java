package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.NewsfeedPost;
import tn.esprit.spring.service.IBsuserService;
import tn.esprit.spring.service.IFileStorageService;
import tn.esprit.spring.service.INewsFeedPostService;

@RestController
@RequestMapping(path ="/api/newsfeed")
public class NewsFeedPostController {
    @Autowired
    INewsFeedPostService iNewsFeedPostService;
    @Autowired
    IBsuserService iBsuserService;
    @Autowired
    IFileStorageService iFileStorageService;


    @GetMapping
    public ResponseEntity<Object> getNewsFeedPosts(){
        return iNewsFeedPostService.getNewsfeedPosts();
    }

    @GetMapping({"/user/{userId}"})
    public ResponseEntity<Object> getNewsFeedPostsByUser(@PathVariable Long userId){
        User user = new User();
        user = iBsuserService.getBsUser(userId);

        return iNewsFeedPostService.getPostsByPostedBy(user);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Object> getNewsFeedPost(@PathVariable Long id){
        return iNewsFeedPostService.getNewsfeedPost(id);
    }

    @PostMapping
    @RequestMapping(path = "/new")
    @ResponseBody
    public ResponseEntity<Object> add(@RequestBody NewsfeedPost newsFeedPost){

       return iNewsFeedPostService.ajouterNewsfeedPost(newsFeedPost);
    }
    @PostMapping
    @RequestMapping(path = "/upload/image")
    @ResponseBody
    public String addimage(NewsfeedPost newsFeedPost,@RequestBody MultipartFile file){
        String message ="";
        try {
            iFileStorageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return message;
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return message;
        }
    }
    @PutMapping
    @RequestMapping(path = "/edit")
    @ResponseBody
    public ResponseEntity<Object> edit(@RequestBody NewsfeedPost newsFeedPost){
       return iNewsFeedPostService.modifierNewsfeedPost(newsFeedPost);
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return iNewsFeedPostService.deleteNewsfeedPost(id);
    }



}
