package server.service;

import entity.Appointment;
import entity.AppointmentResult;
import entity.MedicalCard;
import entity.ObjectFactory;
import entity.parsers.JAXBParser;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.ws.Holder;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collection;

@HandlerChain(file = "security_handler.xml")
@WebService(serviceName="Polyclinic",
        portName="PolyclinicPort",
        endpointInterface="server.service.PolyclinicService",
        targetNamespace = Const.SERVICE_NS)
public class DummyPolyclinicService implements PolyclinicService{
    private ObjectFactory objectFactory = new ObjectFactory();


    @Override
    public MedicalCard getMedicalCard(int cardNumber, String clientToken, Holder<String> serverToken) throws ServiceException {
        JAXBParser parser = new JAXBParser();
        MedicalCard medicalCard = null;
        try {
            medicalCard = parser.loadMedicalCard("src/main/resources/medicalCard.xml");
        } catch (Exception e) {
            throw new ServiceException("Error loading medical card");
        }
        return medicalCard;
    }

    @Override
    public int addMedicalCard(MedicalCard medicalCard, String clientToken, Holder<String> serverToken) {
        return 0;
    }

    @Override
    public Collection<Appointment> getAppointmentsForPatient(int cardNumber, String clientToken, Holder<String> serverToken) {
        return null;
    }

    @Override
    public int addAppointmentForPatient(Appointment appointment, String clientToken, Holder<String> serverToken) {
        return 0;
    }

    @Override
    public Appointment updateAppointmentTime(int appointmentId, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime, String clientToken, Holder<String> serverToken) {
        return null;
    }

    @Override
    public Appointment deleteAppointment(int appointmentId, String clientToken, Holder<String> serverToken) {
        return null;
    }

    @Override
    public int addAppointmentResult(AppointmentResult appointmentResult, String clientToken, Holder<String> serverToken) {
        return 0;
    }

    @Override
    public AppointmentResult updateAppointmentResult(AppointmentResult appointmentResult, String clientToken, Holder<String> serverToken) {
        return null;
    }
}
