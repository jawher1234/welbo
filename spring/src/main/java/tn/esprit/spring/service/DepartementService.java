package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Departement;
import tn.esprit.spring.repository.IDepratmentRepository;

import java.util.List;

@Service
public class DepartementService implements IDepartementService{

    @Autowired
    IDepratmentRepository depratmentRepository;

    @Override
    public Departement addDepartement(Departement departement) {
        depratmentRepository.save(departement);
        return departement;
    }

    @Override
    public void deleteDepartement(Long id) {

        depratmentRepository.deleteById(id);
    }

    @Override
    public List<Departement> getDepartements() {
        return null;
    }

    @Override
    public Departement updateDepartement(Departement departement) {
        return null;
    }

    @Override
    public Departement getDepartement(Long id) {
        return depratmentRepository.findById(id).get();
    }
}
