package server.service;

import com.polyclinic.entities.Appointment;
import com.polyclinic.entities.AppointmentResult;
import com.polyclinic.entities.MedicalCard;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collection;

@WebService(targetNamespace = Const.SERVICE_NS)
public interface PolyclinicService {

    //Medical card methods
    @WebMethod()
    @WebResult(name = "medicalCard", targetNamespace=Const.MEDICAL_CARD_NS)
    public MedicalCard getMedicalCard(@WebParam(name="cardNumber")
                                          @XmlElement(required = true) int cardNumber);

    @WebMethod()
    public int addMedicalCard(@WebParam(name="medicalCard", targetNamespace = Const.MEDICAL_CARD_NS)
                                  @XmlElement(required = true) MedicalCard medicalCard);

    //Appointments methods
    @WebMethod()
    @WebResult(name ="appointment", targetNamespace = Const.APPOINTMENT_NS)
    public Collection<Appointment> getAppointmentsForPatient(@WebParam(name="cardNumber")
                                                                 @XmlElement(required = true) int cardNumber);

    @WebMethod()
    public int addAppointmentForPatient(@WebParam(name="appointment", targetNamespace = Const.APPOINTMENT_NS)
                                            @XmlElement(required = true) Appointment appointment);

    @WebMethod()
    @WebResult(name="appointment", targetNamespace = Const.APPOINTMENT_NS)
    public Appointment updateAppointmentTime(@WebParam(name="appointmentId") @XmlElement(required = true) int appointmentId,
                                             @WebParam(name="startTime") @XmlElement(required = true) XMLGregorianCalendar startTime,
                                             @WebParam(name="endTime") @XmlElement(required = true) XMLGregorianCalendar endTime);

    @WebMethod()
    @WebResult(name="appointment", targetNamespace = Const.APPOINTMENT_NS)
    public Appointment deleteAppointment(@WebParam(name="appointmentId") @XmlElement(required = true) int appointmentId);

    //Appointment results methods
    @WebMethod()
    public int addAppointmentResult(@WebParam(name="appointmentResult", targetNamespace = Const.APPOINTMENT_RESULT_NS)
                                        @XmlElement(required = true) AppointmentResult appointmentResult);

    @WebMethod()
    @WebResult(name="appointmentResult", targetNamespace = Const.APPOINTMENT_RESULT_NS)
    public AppointmentResult updateAppointmentResult(@WebParam(name="appointmentResult", targetNamespace = Const.APPOINTMENT_RESULT_NS)
                                                         @XmlElement(required = true) AppointmentResult appointmentResult);

}
