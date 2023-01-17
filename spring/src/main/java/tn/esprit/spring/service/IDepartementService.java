package tn.esprit.spring.service;

import tn.esprit.spring.entity.Departement;

import java.util.List;

public interface IDepartementService {
    public Departement addDepartement(Departement departement);
    public void deleteDepartement(Long id);
    public List<Departement> getDepartements();
    public Departement updateDepartement(Departement departement);
    public Departement getDepartement(Long id);
}
