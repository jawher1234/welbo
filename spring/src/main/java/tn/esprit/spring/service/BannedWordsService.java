package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.BannedWords;
import tn.esprit.spring.repository.BadWordsRepository;

import java.util.List;
@Service
public class BannedWordsService implements IBannedWordsService{

    @Autowired
    BadWordsRepository badWordsRepository;

    @Override
    public BannedWords ajouterBannedWords(BannedWords bannedWord) {
        return badWordsRepository.save(bannedWord);
    }

    @Override
    public void deleteBannedWords(Long bannedWordId) {
        badWordsRepository.deleteById(bannedWordId);
    }

    @Override
    public List<BannedWords> getBannedWordsList() {
        return (List<BannedWords>) badWordsRepository.findAll();
    }

    @Override
    public BannedWords modifierBannedWords(BannedWords bannedWord) {
        return badWordsRepository.save(bannedWord);
    }

    @Override
    public BannedWords getBannedWords(Long bannedWordId) {
        return badWordsRepository.findById(bannedWordId).get();
    }

}
