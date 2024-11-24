package entity.parsers;

import entity.Appointment;
import entity.AppointmentResult;
import entity.MedicalCard;

public interface XMLLoader {
    public Appointment loadAppointment(final String xmlFileName) throws Exception;

    public AppointmentResult loadAppointmentResult(final String xmlFileName) throws Exception;

    public MedicalCard loadMedicalCard(final String xmlFileName) throws Exception;
}
