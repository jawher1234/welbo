package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Categories;
import tn.esprit.spring.repository.CategoriesRepository;

@Service
public class CategoriesServiceImp implements ICategoriesService{
	@Autowired
	CategoriesRepository CategoriesRepository;
	
	@Override
	public List<Categories> retrieveAllCategories() {
		List<Categories> Categories = (List<Categories>) CategoriesRepository.findAll();
		return Categories;
	}
	
	@Override
	public Categories addCategorie(Categories c) {
		CategoriesRepository.save(c);
		 return c;
	}

	@Override
	public void deleteCategorie(Long id) {
		CategoriesRepository.delete(CategoriesRepository.findById(id).get());
	}

	@Override
	public Categories updateCategorie(Categories c) {
		return CategoriesRepository.save(c);
	}
}
