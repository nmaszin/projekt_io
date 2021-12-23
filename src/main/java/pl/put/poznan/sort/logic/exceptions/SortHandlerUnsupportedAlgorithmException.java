package pl.put.poznan.sort.logic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception which should be thrown when key given by user does not exist in JSON object
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SortHandlerUnsupportedAlgorithmException extends RuntimeException {
    public SortHandlerUnsupportedAlgorithmException(String message) {
        super(message);
    }
}
