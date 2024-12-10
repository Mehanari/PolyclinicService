package server.service.repository;

public class CardDoesNotExistsException extends Exception{
    public CardDoesNotExistsException(String message) {
        super(message);
    }
}
