package server.service;

import entity.Appointment;
import entity.AppointmentResult;
import entity.MedicalCard;
import entity.ObjectFactory;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.ws.Holder;
import server.service.repository.InMemoryPolyclinicRepository;
import server.service.repository.PolyclinicRepository;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collection;

@HandlerChain(file = "security_handler.xml")
@WebService(serviceName="Polyclinic",
        portName="PolyclinicPort",
        endpointInterface="server.service.PolyclinicService",
        targetNamespace = Const.SERVICE_NS)
public class SimplePolyclinicService implements PolyclinicService{
    private ObjectFactory objectFactory = new ObjectFactory();
    private PolyclinicRepository repository = new InMemoryPolyclinicRepository();


    @Override
    public MedicalCard getMedicalCard(int cardNumber, String clientToken, Holder<String> serverToken) throws ServiceException {
        try {
            return repository.getMedicalCard(cardNumber);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int addMedicalCard(MedicalCard medicalCard, String clientToken, Holder<String> serverToken) {
        MedicalCard addedCard = repository.addMedicalCard(medicalCard);
        return addedCard.getCardNumber();
    }

    @Override
    public Collection<Appointment> getAppointmentsForPatient(int cardNumber, String clientToken, Holder<String> serverToken) throws ServiceException {
        try {
            return repository.getAppointmentsForPatient(cardNumber);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int addAppointmentForPatient(Appointment appointment, String clientToken, Holder<String> serverToken) throws ServiceException {
        try {
            Appointment addedAppointment = repository.addAppointmentForPatient(appointment);
            return addedAppointment.getId();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Appointment updateAppointmentTime(int appointmentId, XMLGregorianCalendar startTime,
                                             XMLGregorianCalendar endTime, String clientToken, Holder<String> serverToken) throws ServiceException {
        try {
            Appointment appointment = repository.getAppointment(appointmentId);
            appointment.setStartTime(startTime);
            appointment.setEndTime(endTime);
            return repository.updateAppointment(appointmentId, appointment);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Appointment deleteAppointment(int appointmentId, String clientToken, Holder<String> serverToken) throws ServiceException {
        try {
            return repository.deleteAppointment(appointmentId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int addAppointmentResult(AppointmentResult appointmentResult, String clientToken, Holder<String> serverToken) throws ServiceException {
        try {
            AppointmentResult addedResult = repository.addAppointmentResult(appointmentResult);
            return addedResult.getId();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public AppointmentResult updateAppointmentResult(AppointmentResult appointmentResult, String clientToken, Holder<String> serverToken) throws ServiceException {
        try {
            return repository.updateAppointmentResult(appointmentResult);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
