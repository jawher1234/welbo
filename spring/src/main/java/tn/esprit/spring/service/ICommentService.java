package tn.esprit.spring.service;

import org.springframework.http.ResponseEntity;
import tn.esprit.spring.entity.Comment;

public interface ICommentService {
    public ResponseEntity<Object> ajouterComment(Comment comment);
    public ResponseEntity<Object> deleteComment(Long commentId);
    public ResponseEntity<Object> getComments();
    public ResponseEntity<Object> modifierComment(Comment comment);
    public ResponseEntity<Object> getComment(Long commentId);
    public ResponseEntity<Object> getCommentsByPost(Long postId);

}
