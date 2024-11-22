package server.service;

import com.polyclinic.entities.Appointment;
import com.polyclinic.entities.AppointmentResult;
import com.polyclinic.entities.MedicalCard;
import com.polyclinic.parsers.JAXBParser;
import jakarta.jws.WebService;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collection;

@WebService(serviceName="Polyclinic",
        portName="PolyclinicPort",
        endpointInterface="server.service.PolyclinicService",
        targetNamespace = Const.SERVICE_NS)
public class DummyPolyclinicService implements PolyclinicService{

    @Override
    public MedicalCard getMedicalCard(int cardNumber) {
        JAXBParser parser = new JAXBParser();
        MedicalCard medicalCard = null;
        try{
            medicalCard = parser.loadMedicalCard("src/main/resources/medicalCard.xml");
            System.out.println(medicalCard.getPersonalInfo().getFirstName());
        }
        catch (Exception e){
            System.out.println(e);
        }
        return medicalCard;
    }

    @Override
    public int addMedicalCard(MedicalCard medicalCard) {
        return 0;
    }

    @Override
    public Collection<Appointment> getAppointmentsForPatient(int cardNumber) {
        return null;
    }

    @Override
    public int addAppointmentForPatient(Appointment appointment) {
        return 0;
    }

    @Override
    public Appointment updateAppointmentTime(int appointmentId, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime) {
        return null;
    }

    @Override
    public Appointment deleteAppointment(int appointmentId) {
        return null;
    }

    @Override
    public int addAppointmentResult(AppointmentResult appointmentResult) {
        return 0;
    }

    @Override
    public AppointmentResult updateAppointmentResult(AppointmentResult appointmentResult) {
        return null;
    }
}
