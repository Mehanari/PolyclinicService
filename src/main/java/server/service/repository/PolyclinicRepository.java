package server.service.repository;

import entity.Appointment;
import entity.AppointmentResult;
import entity.MedicalCard;

import java.util.Collection;
import java.util.List;

public interface PolyclinicRepository {

    public MedicalCard addMedicalCard(MedicalCard medicalCard);
    public MedicalCard getMedicalCard(int cardNumber) throws Exception;
    public Collection<Appointment> getAppointmentsForPatient(int cardNumber) throws Exception;
    public Appointment addAppointmentForPatient(Appointment appointment) throws Exception;
    public Appointment getAppointment(int appointmentId) throws Exception;
    public Appointment updateAppointment(int appointmentId, Appointment appointment) throws Exception;
    public Appointment deleteAppointment(int appointmentId) throws Exception;
    public AppointmentResult addAppointmentResult(AppointmentResult appointmentResult) throws Exception;
    public AppointmentResult updateAppointmentResult(AppointmentResult appointmentResult) throws Exception;
}
