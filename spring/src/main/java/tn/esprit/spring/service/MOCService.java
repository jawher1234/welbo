package tn.esprit.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.MembersOfCompany;
import tn.esprit.spring.repository.IMembresOfCompany;
import tn.esprit.spring.helper.CSVHelper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class MOCService {
  @Autowired
  IMembresOfCompany membresOfCompany;


  public void save(MultipartFile file) {
    try {
      List<MembersOfCompany> MOCs = CSVHelper.csvToMOCs(file.getInputStream());
      membresOfCompany.saveAll(MOCs);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<MembersOfCompany> MOCs = (List<MembersOfCompany>) membresOfCompany.findAll();

    ByteArrayInputStream in = CSVHelper.MOCsToCSV(MOCs);
    return in;
  }


  public List<MembersOfCompany> getAll() {
    return (List<MembersOfCompany>) membresOfCompany.findAll();
  }
}
