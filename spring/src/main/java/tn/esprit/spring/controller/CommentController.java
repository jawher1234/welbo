package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.service.ICommentService;

@RestController
@RequestMapping(path ="/api/comments")
public class CommentController {

    @Autowired
    ICommentService iCommentService;


    @GetMapping
    @RequestMapping(path ="/")
    public ResponseEntity<Object> getComments(){
        return iCommentService.getComments();
    }
    @GetMapping({"/{id}"})
    public ResponseEntity<Object> getComment(@PathVariable Long id){
        return iCommentService.getComment(id);
    }

    @PostMapping
    @RequestMapping(path = "/new")
    @ResponseBody
    public ResponseEntity<Object> add(@RequestBody Comment comment){
        return iCommentService.ajouterComment(comment);
    }

    @PutMapping
    @RequestMapping(path = "/edit")
    @ResponseBody
    public ResponseEntity<Object> edit(@RequestBody Comment comment){
        return iCommentService.modifierComment(comment);
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return iCommentService.deleteComment(id);
    }

    // comments by post
    @GetMapping("/post/{postId}")
    public ResponseEntity<Object> commentByPost(@PathVariable(value = "postId") Long postId) {
        return   iCommentService.getCommentsByPost(postId);
    }
}
