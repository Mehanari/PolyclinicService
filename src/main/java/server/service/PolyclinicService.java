package server.service;

import com.polyclinic.entities.Appointment;
import com.polyclinic.entities.AppointmentResult;
import com.polyclinic.entities.MedicalCard;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collection;

@WebService(targetNamespace = Const.SERVICE_NS)
public interface PolyclinicService {

    //Medical card methods
    @WebMethod()
    @WebResult(targetNamespace=Const.MEDICAL_CARD_NS)
    public MedicalCard getMedicalCard(@WebParam(name="cardNumber") int cardNumber);

    @WebMethod()
    public int addMedicalCard(@WebParam(name="medicalCard", targetNamespace = Const.MEDICAL_CARD_NS) MedicalCard medicalCard);

    //Appointments methods
    @WebMethod()
    @WebResult(targetNamespace = Const.APPOINTMENT_NS)
    public Collection<Appointment> getAppointmentsForPatient(@WebParam(name="cardNumber") int cardNumber);

    @WebMethod()
    public int addAppointmentForPatient(@WebParam(name="appointment", targetNamespace = Const.APPOINTMENT_NS) Appointment appointment);

    @WebMethod()
    @WebResult(targetNamespace = Const.APPOINTMENT_NS)
    public Appointment updateAppointmentTime(@WebParam(name="appointmentId") int appointmentId,
                                             @WebParam(name="startTime") XMLGregorianCalendar startTime,
                                             @WebParam(name="endTime") XMLGregorianCalendar endTime);

    @WebMethod()
    @WebResult(targetNamespace = Const.APPOINTMENT_NS)
    public Appointment deleteAppointment(@WebParam(name="appointmentId") int appointmentId);

    //Appointment results methods
    @WebMethod()
    public int addAppointmentResult(@WebParam(name="appointmentResult", targetNamespace = Const.APPOINTMENT_RESULT_NS) AppointmentResult appointmentResult);

    @WebMethod()
    @WebResult(targetNamespace = Const.APPOINTMENT_RESULT_NS)
    public AppointmentResult updateAppointmentResult(@WebParam(name="appointmentResult", targetNamespace = Const.APPOINTMENT_RESULT_NS) AppointmentResult appointmentResult);

}
