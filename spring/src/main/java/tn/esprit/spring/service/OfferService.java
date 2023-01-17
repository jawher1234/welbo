package tn.esprit.spring.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.Collaborator;
import tn.esprit.spring.entity.Offer;
import tn.esprit.spring.repository.OfferRepository;
import tn.esprit.spring.response.ResponseHandler;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OfferService implements IOfferService{
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    @Autowired
    OfferRepository offerRepository;
    @Override
    public ResponseEntity<Object> ajouterOffer(Offer offer) {
        try{
            if(offer.getImageUrl() != null){
                //get file extension
                String fileextension = FilenameUtils.getExtension(Paths.get(offer.getImageUrl()).getFileName().toString()).toLowerCase();
                //file must be an image
                if (fileextension.equals("png") || fileextension.equals("jpg") || fileextension.equals("jpeg") ){
                    offer.setImageUrl(uploadImgAndGetUrl(offer.getImageUrl()));
                }else {
                    return ResponseHandler.generateResponse("file extension "+fileextension+" is not allowed this is the list of allowed extensions: [png, jpg, jpeg]", HttpStatus.MULTI_STATUS, null);
                }
            }else {
                return ResponseHandler.generateResponse("offer image is required !", HttpStatus.MULTI_STATUS, null);
            }

            if(offer.getDescription().length()<=0){
                return ResponseHandler.generateResponse("You should set offer description!", HttpStatus.NOT_FOUND, offer);
            }
            if(offer.getTitle().length()<=0){
                return ResponseHandler.generateResponse("You should set offer title!", HttpStatus.MULTI_STATUS, offer);
            }
            if(!offer.getStartsAt().isBefore(offer.getExpiresAt())){
                return ResponseHandler.generateResponse("error : expires at < starts at!", HttpStatus.MULTI_STATUS, offer);
            }
            if(offer.getExpiresAt().isBefore(LocalDateTime.now())){
                return ResponseHandler.generateResponse("error : expires at must be > dcurrent date!", HttpStatus.MULTI_STATUS, offer);
            }

            offer.setState(false);
            offer.setCreatedAt(LocalDateTime.now());
            offerRepository.save(offer);
            return ResponseHandler.generateResponse("Successfully added offer!", HttpStatus.OK, offer);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }


    }

    @Override
    public ResponseEntity<Object> deleteOffer(Long offerId) {
        try{
            offerRepository.deleteById(offerId);
            return ResponseHandler.generateResponse("Successfully deleted offer!", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }


    }

    @Override
    public ResponseEntity<Object> getOffers() {
        List<Offer> offers = (List<Offer>) offerRepository.findAll();
        LocalDateTime currentDate = LocalDateTime.now();
       // createExcel();
      //  dataFromExcel("howtodoinjava_demo.xlsx");
        for(Offer offer : offers){
            if((offer.getStartsAt().isBefore(currentDate) || offer.getStartsAt().equals(currentDate) ) && ( offer.getExpiresAt().isAfter(currentDate) || offer.getExpiresAt().equals(currentDate)) ){
                offer.setState(true);
                if(offer.getQuantity() == 0){
                    offer.setState(false);
                }
                offerRepository.save(offer);
            }else {
                offer.setState(false);
                offerRepository.save(offer);
            }

        }
        offers = (List<Offer>) offerRepository.findAll();
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, (List<Offer>) offers);
    }
    @Override
    public ResponseEntity<Object> getOffersByState(boolean state) {
        List<Offer> offers = (List<Offer>) offerRepository.findAll();
        LocalDateTime currentDate = LocalDateTime.now();
        for(Offer offer : offers){
            if((offer.getStartsAt().isBefore(currentDate) || offer.getStartsAt().equals(currentDate) ) &&( offer.getExpiresAt().isAfter(currentDate) || offer.getExpiresAt().equals(currentDate)) ){
                offer.setState(true);
                offerRepository.save(offer);
            }else {
                offer.setState(false);
                offerRepository.save(offer);
            }
        }
        offers = (List<Offer>) offerRepository.findAllByState(state);
        return ResponseHandler.generateResponse(!offers.isEmpty()?"Success":"No offers with state = "+state, HttpStatus.OK, (List<Offer>) offers);
    }

    @Override
    public void uploadFile(MultipartFile file) {

    }

    @Override
    public ResponseEntity<Object> modifierOffer( Offer offer) {
        LocalDateTime currentDate = LocalDateTime.now();
        if (offerRepository.findById(offer.getId()).isEmpty()){
            return ResponseHandler.generateResponse("No record with this id", HttpStatus.MULTI_STATUS, null);

        }
        if(offer.getDescription().length()<=0){
            return ResponseHandler.generateResponse("You should set offer description!", HttpStatus.NOT_FOUND, offer);
        }
        if(offer.getTitle().length()<=0){
            return ResponseHandler.generateResponse("You should set offer title!", HttpStatus.MULTI_STATUS, offer);
        }
        if(!offer.getStartsAt().isBefore(offer.getExpiresAt())){
            return ResponseHandler.generateResponse("error : expires at < starts at!", HttpStatus.MULTI_STATUS, offer);
        }
        if(offer.getExpiresAt().isBefore(LocalDateTime.now())){
            return ResponseHandler.generateResponse("error : expires at must be > dcurrent date!", HttpStatus.MULTI_STATUS, offer);
        }
        if(offer.getStartsAt().isBefore(currentDate) && offer.getExpiresAt().isAfter(currentDate)){
            offer.setState(true);
        }else{
            offer.setState(false);
        }
        offerRepository.save(offer);
        return ResponseHandler.generateResponse("Updated successfully!", HttpStatus.OK, offer);

    }

    @Override
    public ResponseEntity<Object> getOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId).get();
        if(offer.getQuantity() == 0){
            return ResponseHandler.generateResponse("Epuised !", HttpStatus.OK, offer );
        }

        return ResponseHandler.generateResponse("success", HttpStatus.OK, offer );
    }

    @Override
    public ResponseEntity<Object> updateOfferState(Long offerId) {
        Offer offer = offerRepository.findById(offerId).get();
        offer.setState(!offer.getState());
        offerRepository.save(offer);
        return ResponseHandler.generateResponse("state updated!", HttpStatus.OK, offer);

    }
    public String uploadImgAndGetUrl(String imageUrlFromComputer) throws IOException {
        byte[] image = Files.readAllBytes(Paths.get(imageUrlFromComputer));
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = currentDateTime.format(formatter);
        FileUtils.writeByteArrayToFile(new File("images/offers/"+formattedDateTime+Paths.get(imageUrlFromComputer).getFileName()), image);
        return formattedDateTime+Paths.get(imageUrlFromComputer).getFileName().toString();
    }
    @Override
    public void createExcel(String saveToPath){
        int i =2;
        List<Offer> offers = (List<Offer>) offerRepository.findAll();
    //Blank workbook
    XSSFWorkbook workbook = new XSSFWorkbook();

    //Create a blank sheet
    XSSFSheet sheet = workbook.createSheet("offers");

    //This data needs to be written (Object[])
    Map<String, Object[]> data = new TreeMap<String, Object[]>();

   data.put("1", new Object[] {"ID", "COLLABORATOR","TITLE", "DESCRIPTION","CREATED AT","STARTS AT","EXPRES AT","QUANTITY","STATE"});
    for(Offer o : offers ){
        data.put(String.valueOf(i), new Object[] {o.getId(), o.getCollaborator().getCompany(),o.getTitle(), o.getDescription(),o.getCreatedAt().format(formatter),o.getStartsAt().format(formatter),o.getExpiresAt().format(formatter),o.getQuantity(),o.getState()});
        i++;
    }


    //Iterate over data and write to sheet
    Set<String> keyset = data.keySet();
    int rownum = 0;
    for (String key : keyset)
    {

        Row row = sheet.createRow(rownum++);
        Object [] objArr = data.get(key);
        int cellnum = 0;
        for (Object obj : objArr)
        {
            Cell cell = row.createCell(cellnum++);
            CellStyle style = workbook.createCellStyle();

            if(obj instanceof String)
                cell.setCellValue((String)obj);
            else if(obj instanceof Integer)
                cell.setCellValue((Integer)obj);
            else if(obj instanceof Long)
                cell.setCellValue((Long)obj);
            else if(obj instanceof LocalDateTime)
                cell.setCellValue((LocalDateTime) obj);
            else if(obj instanceof Boolean){
                cell.setCellValue((Boolean) obj);
                if (obj.equals(true)){
                    style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                }else {
                    style.setFillForegroundColor(IndexedColors.RED1.getIndex());
                }

                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cell.setCellStyle(style);
            }
        }
    }
    try
    {
        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(new File(saveToPath));
        workbook.write(out);
        out.close();
        System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
}
    @Override
    public void dataFromExcel(String fileLocation) {
        try
        {
        Boolean isSetId = false;
            Offer offer = new Offer();
            offer.setId(null);
            Collaborator collaborator = new Collaborator();
            FileInputStream file = new FileInputStream(new File(fileLocation));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int j =0;
            int k=0;


            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();

                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                int i = 0;

                if(j>0){

                    while (cellIterator.hasNext())
                    {

                        i++;
                        Cell cell = cellIterator.next();

                        switch (cell.getColumnIndex())
                        {
                            case 0:
                                    isSetId = true;
                                    offer.setId((long) cell.getNumericCellValue());
                                    System.out.print(" id-> "+cell.getColumnIndex() +" "+cell.getNumericCellValue());
                                break;
                            case 1:

                                collaborator.setCompany(cell.getStringCellValue());
                                collaborator.setId(1L);
                                offer.setCollaborator(collaborator);
                                System.out.print(" company-> "+cell.getColumnIndex() +" "+cell.getStringCellValue());
                                break;
                            case 2:
                                offer.setTitle(cell.getStringCellValue());
                                System.out.print(" title-> "+cell.getColumnIndex() +" "+cell.getStringCellValue());                                break;
                            case 3:
                                offer.setDescription(cell.getStringCellValue());
                                System.out.print(" description-> "+cell.getColumnIndex() +" "+cell.getStringCellValue());                                break;
                            case 4:
                                LocalDateTime createdAt = LocalDateTime.parse(cell.getStringCellValue(), formatter);
                                offer.setCreatedAt(createdAt);
                                System.out.print(cell.getColumnIndex() +" "+cell.getStringCellValue());                                break;
                            case 5:
                                LocalDateTime startsat = LocalDateTime.parse(cell.getStringCellValue(), formatter);
                                offer.setStartsAt(startsat);
                                System.out.print(cell.getColumnIndex() +" "+cell.getStringCellValue());                                break;
                            case 6:
                                LocalDateTime expiresat = LocalDateTime.parse(cell.getStringCellValue(), formatter);
                                offer.setExpiresAt(expiresat);
                                System.out.print(cell.getColumnIndex() +" "+cell.getStringCellValue());                                break;
                            case 7:
                                offer.setQuantity((int) cell.getNumericCellValue());
                                System.out.print(" qt-> "+cell.getColumnIndex() +" "+cell.getNumericCellValue());                                break;
                            case 8:
                                offer.setState(cell.getBooleanCellValue());
                                System.out.print(" etat-> "+cell.getColumnIndex() +" "+cell.getBooleanCellValue());
                                break;
                        }
                        if (!isSetId){
                            offer.setId(0L);
                        }


                    }

                    offerRepository.save(offer);
                }
                j++;
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
