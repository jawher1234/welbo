package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.ForumPosts;
import tn.esprit.spring.entity.Likes;

@Repository
public interface ForumPostsRepository extends CrudRepository <ForumPosts,Long>{
//showing posts by categorie
	@Query(value = "SELECT * FROM `forum_posts` WHERE categorie_id=:idcateg",nativeQuery = true)
	List<ForumPosts> retrievePostsByCategorie(@Param("idcateg") Long idcateg);
	
	@Query(value = "SELECT * FROM `likes` WHERE forum_post_id=:forumpostid",nativeQuery = true)
	List<Likes> retrievePostLikes(@Param("forumpostid") Long forumpostid);
}