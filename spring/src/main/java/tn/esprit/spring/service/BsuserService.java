package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.BsUserRepository;

import java.util.List;

@Service
public class BsuserService implements IBsuserService{
    @Autowired
    BsUserRepository bsuserRepository;


    @Override
    public User ajouterBsUser(User bsuser) {
        bsuserRepository.save(bsuser);
        return bsuser;
    }



    @Override
    public void deleteBsUser(Long bsuserId) {
        bsuserRepository.deleteById(bsuserId);

    }

    @Override
    public List<User> getBsUsers() {

        return (List<User>) bsuserRepository.findAll();
    }

    @Override
    public User modifierBsUser(User bsuser) {
        bsuserRepository.save(bsuser);
        return bsuser;
    }

    @Override
    public User getBsUser(Long bsuserId) {
        return bsuserRepository.findById(bsuserId).get();
    }

}
