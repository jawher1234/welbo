package tn.esprit.spring.helper;


import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.MembersOfCompany;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "nid", "name" , "lastname"};

  public static boolean hasCSVFormat(MultipartFile file) {
    if (!TYPE.equals(file.getContentType())) {
      return false;
    }
    return true;
  }

  public static List<MembersOfCompany> csvToMOCs(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<MembersOfCompany> MOCs = new ArrayList<MembersOfCompany>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      for (CSVRecord csvRecord : csvRecords) {
        MembersOfCompany MOC;

        MOC = new MembersOfCompany(csvRecord.get(0),csvRecord.get(1),csvRecord.get(2));

        MOCs.add(MOC);
      }
      return MOCs;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  public static ByteArrayInputStream MOCsToCSV(List<MembersOfCompany> MOCs) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (MembersOfCompany MOC : MOCs) {
        List<String> data = Arrays.asList(
                MOC.getLastName(),
                MOC.getName(),
                MOC.getNid()
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }

}
