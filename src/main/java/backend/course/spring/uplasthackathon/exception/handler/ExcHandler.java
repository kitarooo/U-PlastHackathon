package backend.course.spring.uplasthackathon.exception.handler;

import backend.course.spring.uplasthackathon.exception.IncorrectDataException;
import backend.course.spring.uplasthackathon.exception.NotFoundException;
import backend.course.spring.uplasthackathon.exception.UnauthorizedException;
import backend.course.spring.uplasthackathon.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ExceptionResponse unauthorizedException(UserAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse unauthorizedException(NotFoundException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse unauthorizedException(UnauthorizedException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(IncorrectDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse unauthorizedException(IncorrectDataException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e.getClass().getName(), e.getMessage());
    }
}
