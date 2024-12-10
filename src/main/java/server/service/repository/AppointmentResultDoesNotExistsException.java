package server.service.repository;

public class AppointmentResultDoesNotExistsException extends Exception{
    public AppointmentResultDoesNotExistsException(String message) {
        super(message);
    }
}
