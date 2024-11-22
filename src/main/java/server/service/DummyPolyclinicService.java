package server.service;

import com.polyclinic.entities.Appointment;
import com.polyclinic.entities.AppointmentResult;
import com.polyclinic.entities.MedicalCard;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebService;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collection;

@HandlerChain(file = "security_handler.xml")
@WebService(serviceName="Polyclinic",
        portName="PolyclinicPort",
        endpointInterface="server.service.PolyclinicService",
        targetNamespace = Const.SERVICE_NS)
public class DummyPolyclinicService implements PolyclinicService{


    @Override
    public MedicalCard getMedicalCard(int cardNumber, String userToken) {
        return null;
    }

    @Override
    public int addMedicalCard(MedicalCard medicalCard, String userToken) {
        return 0;
    }

    @Override
    public Collection<Appointment> getAppointmentsForPatient(int cardNumber, String userToken) {
        return null;
    }

    @Override
    public int addAppointmentForPatient(Appointment appointment, String userToken) {
        return 0;
    }

    @Override
    public Appointment updateAppointmentTime(int appointmentId, XMLGregorianCalendar startTime, XMLGregorianCalendar endTime, String userToken) {
        return null;
    }

    @Override
    public Appointment deleteAppointment(int appointmentId, String userToken) {
        return null;
    }

    @Override
    public int addAppointmentResult(AppointmentResult appointmentResult, String userToken) {
        return 0;
    }

    @Override
    public AppointmentResult updateAppointmentResult(AppointmentResult appointmentResult, String userToken) {
        return null;
    }
}
