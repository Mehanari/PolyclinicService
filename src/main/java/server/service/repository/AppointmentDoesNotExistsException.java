package server.service.repository;

public class AppointmentDoesNotExistsException extends Exception{
    public AppointmentDoesNotExistsException(String message) {
        super(message);
    }
}
