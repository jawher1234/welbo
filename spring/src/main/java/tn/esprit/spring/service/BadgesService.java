package tn.esprit.spring.service;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entity.Badges;
import tn.esprit.spring.repository.BadgesRepository;
@Service
@Slf4j
public class BadgesService implements BadgesServiceImpl {	
	@Autowired
	BadgesRepository badgesRepository;
	//Get:http://localhost:8083/PIDEV/badge/retrieve-All-Badges
	@Override
	public List<Badges> retrieveAllBadges() {		
	List<Badges>badges=(List<Badges>)badgesRepository.findAll();
		for (Badges badge : badges)
		{
							log.info("badges :" + badges);
		}
return badges;
	}
	//Post:http://localhost:8083/PIDEV/badge/add-Badge
	@Override
	public Badges addbadge(Badges b) {
		return badgesRepository.save(b);
    }
	//Delete:http://localhost:8083/PIDEV/badge/remove-Badges/5
	@Override
	public void deleteBadges(Integer id) {
		badgesRepository.deleteById(id);
		
	}
	//Put:http://localhost:8083/PIDEV/badge/modify-Badges
	@Override
	public Badges updateBadges(Badges b) {
		return badgesRepository.save(b);
	}

	@Override
	    public Badges retrieveBadges(Integer id) {
		Badges badge = badgesRepository.findById(id).orElse(null);
		System.out.println("Badges :" + badge);
		return badge;
	}
	
	@Override
		 public Badges getFile(Integer id) {
		 return badgesRepository.findById(id).get();
		 }
	
		 @Override
		public Stream<Badges> getAllFiles() {
		return  badgesRepository.findAll().stream();
		}
		@Override
	public Badges store(MultipartFile file) throws IOException {
	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Badges badges = new Badges(file.getContentType(), file.getBytes(), fileName);
				   return badgesRepository.save(badges);
				  }
		//TRI:http://localhost:8083/PIDEV/badge/getAllBadgesBynom
		@Override
		public List getAllBadgesBynom() {
			return badgesRepository.getAllBadgesBynom();
		
}
}

		


