package cl.danoespinoza.rickandmorty.exception;

import com.google.gson.GsonBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public ErrorResponse handleNotFound(final HttpClientErrorException ex) {
        ErrorApi error = new GsonBuilder().create().fromJson(ex.getResponseBodyAsString(), ErrorApi.class);
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), error.getError());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected internal server error occurred");
    }
}
