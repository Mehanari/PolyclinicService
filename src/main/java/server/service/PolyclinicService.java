package server.service;

import entity.Appointment;
import entity.AppointmentResult;
import entity.MedicalCard;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.ws.Holder;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collection;

@WebService(targetNamespace = Const.SERVICE_NS)
public interface PolyclinicService {

    // Medical card methods
    @WebMethod
    @WebResult(name="medicalCard", targetNamespace = Const.MEDICAL_CARD_NS)
    MedicalCard getMedicalCard(
            @WebParam(name = "cardNumber") @XmlElement(required = true) int cardNumber,
            @WebParam(name = "clientToken", header = true) String clientToken,
            @WebParam(name = "serverToken", header = true, mode = WebParam.Mode.OUT)
            Holder<String> serverToken) throws ServiceException;

    @WebMethod
    int addMedicalCard(
            @WebParam(name = "medicalCard", targetNamespace = Const.MEDICAL_CARD_NS) MedicalCard medicalCard,
            @WebParam(name = "clientToken", header = true) String clientToken,
            @WebParam(name = "serverToken", header = true, mode = WebParam.Mode.OUT)
            Holder<String> serverToken) throws ServiceException;

    //Appointments methods
    @WebMethod
    @WebResult(name = "appointment", targetNamespace = Const.APPOINTMENT_NS)
    Collection<Appointment> getAppointmentsForPatient(
            @WebParam(name = "cardNumber") int cardNumber,
            @WebParam(name = "clientToken", header = true) String clientToken,
            @WebParam(name = "serverToken", header = true, mode = WebParam.Mode.OUT)
            Holder<String> serverToken) throws ServiceException;

    @WebMethod
    int addAppointmentForPatient(
            @WebParam(name = "appointment", targetNamespace = Const.APPOINTMENT_NS) Appointment appointment,
            @WebParam(name = "clientToken", header = true) String clientToken,
            @WebParam(name = "serverToken", header = true, mode = WebParam.Mode.OUT)
            Holder<String> serverToken) throws ServiceException;

    @WebMethod
    @WebResult(name = "appointment", targetNamespace = Const.APPOINTMENT_NS)
    Appointment updateAppointmentTime(
            @WebParam(name = "appointmentId") int appointmentId,
            @WebParam(name = "startTime") XMLGregorianCalendar startTime,
            @WebParam(name = "endTime") XMLGregorianCalendar endTime,
            @WebParam(name = "clientToken", header = true) String clientToken,
            @WebParam(name = "serverToken", header = true, mode = WebParam.Mode.OUT)
            Holder<String> serverToken) throws ServiceException;

    @WebMethod
    @WebResult(name = "appointment", targetNamespace = Const.APPOINTMENT_NS)
    Appointment deleteAppointment(
            @WebParam(name = "appointmentId") int appointmentId,
            @WebParam(name = "clientToken", header = true) String clientToken,
            @WebParam(name = "serverToken", header = true, mode = WebParam.Mode.OUT)
            Holder<String> serverToken) throws ServiceException;

    // Appointment results methods
    @WebMethod
    int addAppointmentResult(
            @WebParam(name = "appointmentResult", targetNamespace = Const.APPOINTMENT_RESULT_NS) AppointmentResult appointmentResult,
            @WebParam(name = "clientToken", header = true) String clientToken,
            @WebParam(name = "serverToken", header = true, mode = WebParam.Mode.OUT)
            Holder<String> serverToken) throws ServiceException;

    @WebMethod
    @WebResult(name = "appointmentResult", targetNamespace = Const.APPOINTMENT_RESULT_NS)
    AppointmentResult updateAppointmentResult(
            @WebParam(name = "appointmentResult", targetNamespace = Const.APPOINTMENT_RESULT_NS) AppointmentResult appointmentResult,
            @WebParam(name = "clientToken", header = true) String clientToken,
            @WebParam(name = "serverToken", header = true, mode = WebParam.Mode.OUT)
            Holder<String> serverToken) throws ServiceException;
}
