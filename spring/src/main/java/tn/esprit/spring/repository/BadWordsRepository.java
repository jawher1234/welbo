package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entity.BannedWords;

public interface BadWordsRepository extends CrudRepository<BannedWords,Long> {
}
