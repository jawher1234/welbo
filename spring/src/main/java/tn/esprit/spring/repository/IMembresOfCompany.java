package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entity.MembersOfCompany;

public interface IMembresOfCompany extends CrudRepository<MembersOfCompany,Long> {

    boolean existsByNid(String nid);
}
