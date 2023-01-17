package tn.esprit.spring.service;

import tn.esprit.spring.entity.User;

import java.util.List;

public interface IBsuserService {
    public User ajouterBsUser(User bsuser);
    public void deleteBsUser(Long bsuserId);
    public List<User> getBsUsers();
    public User modifierBsUser(User bsuser);
    public User getBsUser(Long bsuserId);
}
