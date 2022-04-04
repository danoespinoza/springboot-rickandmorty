package cl.danoespinoza.rickandmorty.exception;

import javax.validation.ConstraintViolationException;

import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHandlers {

    private Logger log = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public ErrorResponse handleNotFound(final HttpClientErrorException ex) {
        ErrorApi error = new GsonBuilder().create().fromJson(ex.getResponseBodyAsString(), ErrorApi.class);
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), error.getError());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ErrorResponse handleConstraint(final ConstraintViolationException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getConstraintViolations().iterator().next().getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex) {
        log.error("Unexpected Error", ex);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected internal server error occurred");
    }
}
