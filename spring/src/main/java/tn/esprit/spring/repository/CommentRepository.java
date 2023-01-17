package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.NewsfeedPost;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
    List<Comment> findByNewsfeedPost(NewsfeedPost post);
}
