package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Badges;

@Repository
public interface BadgesRepository extends JpaRepository<Badges, Integer> {
	
	
	@Query(value ="SELECT * FROM `badges`  order by nom asc ", nativeQuery = true)
	
	public List<String> getAllBadgesBynom();

}
