package entity.parsers;
import entity.Appointment;
import entity.AppointmentResult;
import entity.MedicalCard;

public interface XMLParser extends XMLLoader {
    public void saveAppointment(Appointment appointment, final String xmlFileName) throws Exception;

    public void saveAppointmentResult(AppointmentResult appointmentResult, final String xmlFileName) throws Exception;

    public void saveMedicalCard(MedicalCard medicalCard, final String xmlFileName) throws Exception;
}
