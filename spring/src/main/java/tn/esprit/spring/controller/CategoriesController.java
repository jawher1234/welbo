package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Categories;
import tn.esprit.spring.service.ICategoriesService;

@RestController
@RequestMapping("/categorie")
public class CategoriesController {
	@Autowired
	ICategoriesService CategoriesService;
	
	//http://localhost:8083/PIDEV/categorie/retrieve-all-categories
	@GetMapping("/retrieve-all-categories")
	public List<Categories> getCategories() {
	List<Categories> listCategories = CategoriesService.retrieveAllCategories();
	return listCategories;
	}
	
	//http://localhost:8083/PIDEV/categorie/add-categorie
	@PostMapping("/add-categorie")
	@ResponseBody
	public Categories addCategorie(@RequestBody Categories c)
	{
		Categories Categorie = CategoriesService.addCategorie(c);
		return Categorie;
	}
	
	//http://localhost:8083/PIDEV/categorie/remove-categorie/{id}
	@DeleteMapping("/remove-categorie/{id}")
	@ResponseBody
	public void removeCategorie(@PathVariable("id") Long id) {
		CategoriesService.deleteCategorie(id);
	}
	
	//http://localhost:8083/PIDEV/categorie/modify-categorie
	@PutMapping("/modify-categorie")
	@ResponseBody
	public Categories modifyCategorie(@RequestBody Categories Categorie) {
	return CategoriesService.updateCategorie(Categorie);
	}
}
