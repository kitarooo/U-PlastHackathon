package backend.course.spring.uplasthackathon.exception;

public class CatalogAlreadyExistException extends RuntimeException{
    public CatalogAlreadyExistException(String message) {
        super(message);
    }
}
