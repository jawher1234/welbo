package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.NewsFeedPostRepository;
import tn.esprit.spring.response.ResponseHandler;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService implements ICommentService{
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    NewsFeedPostRepository newsFeedPostRepository;
    @Autowired
    IBannedWordsService iBannedWordsService;

    @Override
    public ResponseEntity<Object> ajouterComment(Comment comment) {
        if(comment.getContent().length() < 1){
            return ResponseHandler.generateResponse("comment content is empty", HttpStatus.MULTI_STATUS, comment);
        }else {
            Comment addedComment = new Comment();
            boolean allowed = true;
            String unallowedWords ="";
            String originalString = comment.getContent();
            String formattedString = originalString.toLowerCase();
            List<BannedWords> bannedWords = iBannedWordsService.getBannedWordsList();

            for (BannedWords word : bannedWords){
                if( formattedString.contains(word.getWord())){
                    allowed = false;
                    unallowedWords = word.getWord() + ", "+unallowedWords;
                }
            }
            if (allowed){
                comment.setUpdatedAt(LocalDateTime.now());
                comment.setCreatedAt(LocalDateTime.now());
                commentRepository.save(comment);
            }else{
                return ResponseHandler.generateResponse("You are not allowed to use "+unallowedWords, HttpStatus.MULTI_STATUS, null);

            }

        }

        return ResponseHandler.generateResponse("comment added successfullu", HttpStatus.OK, comment);
    }

    @Override
    public ResponseEntity<Object> deleteComment(Long commentId) {

        try{
            commentRepository.deleteById(commentId);
            return ResponseHandler.generateResponse("Comment successfully deleted !", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @Override
    public ResponseEntity<Object> getComments() {
        try{
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, (List<Comment>) commentRepository.findAll());
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @Override
    public ResponseEntity<Object> modifierComment( Comment comment) {
        if (commentRepository.findById(comment.getId()).isEmpty()){
            return ResponseHandler.generateResponse("No record with this id", HttpStatus.MULTI_STATUS, null);
        }
        if(comment.getContent().length() < 1){
            return ResponseHandler.generateResponse("comment content is empty", HttpStatus.MULTI_STATUS, comment);
        }else {
            Comment addedComment = new Comment();
            boolean allowed = true;
            String unallowedWords ="";
            String originalString = comment.getContent();
            String formattedString = originalString.toLowerCase();
            List<BannedWords> bannedWords = iBannedWordsService.getBannedWordsList();

            for (BannedWords word : bannedWords){
                if( formattedString.contains(word.getWord())){
                    allowed = false;
                    unallowedWords = word.getWord() + ", "+unallowedWords;
                }
            }
            if (allowed){
                comment.setUpdatedAt(LocalDateTime.now());
                commentRepository.save(comment);
            }
        }


        return ResponseHandler.generateResponse("comment edited successfully", HttpStatus.OK, comment);
    }

    @Override
    public ResponseEntity<Object> getComment(Long commentId) {
        try{
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, commentRepository.findById(commentId).get());
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @Override
    public ResponseEntity<Object> getCommentsByPost(Long postId) {
        try{
            NewsfeedPost post = newsFeedPostRepository.findById(postId).get();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, commentRepository.findByNewsfeedPost(post));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }
}
