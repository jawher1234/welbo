package tn.esprit.spring.service;

import tn.esprit.spring.entity.BannedWords;

import java.util.List;

public interface IBannedWordsService {
    public BannedWords ajouterBannedWords(BannedWords bannedWord);
    public void deleteBannedWords(Long bannedWordId);
    public List<BannedWords> getBannedWordsList();
    public BannedWords modifierBannedWords(BannedWords bannedWord);
    public BannedWords getBannedWords(Long bannedWordId);
}
