package pl.put.poznan.sort.logic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception which should be thrown when key given by user does not exist in JSON object
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SortableObjectKeyNotExistsException extends RuntimeException {
    public SortableObjectKeyNotExistsException(String message) {
        super(message);
    }
}
