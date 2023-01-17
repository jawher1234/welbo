package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.User;
@Repository
public interface BsUserRepository extends CrudRepository<User,Long> {
}
