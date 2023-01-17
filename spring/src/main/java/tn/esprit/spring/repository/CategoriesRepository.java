package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Categories;

@Repository
public interface CategoriesRepository extends CrudRepository <Categories,Long>{
	
}