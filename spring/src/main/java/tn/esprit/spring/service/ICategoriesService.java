package tn.esprit.spring.service;

import java.util.List;
import tn.esprit.spring.entity.Categories;

public interface ICategoriesService {
	List<Categories> retrieveAllCategories();
	Categories  addCategorie(Categories c);
	void deleteCategorie(Long id);
	Categories updateCategorie(Categories u);
}