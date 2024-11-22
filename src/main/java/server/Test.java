package server;

import com.polyclinic.entities.MedicalCard;
import com.polyclinic.parsers.JAXBParser;

public class Test {
    public static void main(String[] args) {
        JAXBParser parser = new JAXBParser();
        MedicalCard medicalCard;
        try{
            medicalCard = parser.loadMedicalCard("src/main/resources/medicalCard.xml");
            System.out.println(medicalCard.getPersonalInfo().getFirstName());
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
