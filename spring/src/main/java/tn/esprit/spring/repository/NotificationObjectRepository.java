package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.NotificationObject;

@Repository
public interface NotificationObjectRepository extends CrudRepository <NotificationObject,Long>{

}
