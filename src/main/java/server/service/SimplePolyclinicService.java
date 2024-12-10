package server.service;

import entity.Appointment;
import entity.AppointmentResult;
import entity.MedicalCard;
import entity.ObjectFactory;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.Holder;
import jakarta.xml.ws.soap.SOAPFaultException;
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
    public int addMedicalCard(MedicalCard medicalCard, String clientToken, Holder<String> serverToken) throws ServiceException {
        if (medicalCard == null) {
            throw new SOAPFaultException(createClientFault("Medical card is null"));
        }
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
        if (appointment == null) {
            throw new SOAPFaultException(createClientFault("Appointment is null"));
        }
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
        if (startTime == null || endTime == null) {
            throw new SOAPFaultException(createClientFault("Start time or end time is null"));
        }
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
        if (appointmentResult == null) {
            throw new SOAPFaultException(createClientFault("Appointment result is null"));
        }
        try {
            AppointmentResult addedResult = repository.addAppointmentResult(appointmentResult);
            return addedResult.getId();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public AppointmentResult updateAppointmentResult(AppointmentResult appointmentResult, String clientToken, Holder<String> serverToken) throws ServiceException {
        if (appointmentResult == null) {
            throw new SOAPFaultException(createClientFault("Appointment result is null"));
        }
        try {
            return repository.updateAppointmentResult(appointmentResult);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private SOAPFault createClientFault(String message) {
        try {
            String code = "Client";
            SOAPFault fault = SOAPFactory.newInstance().createFault();
            fault.setFaultString(message);
            fault.setFaultCode(code);
            return fault;
        } catch (SOAPException e) {
            throw new RuntimeException(e);
        }
    }
}
