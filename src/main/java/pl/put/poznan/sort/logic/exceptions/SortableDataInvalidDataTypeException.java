package pl.put.poznan.sort.logic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception which should be thrown when types of compared elements are inconsistent
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SortableDataInvalidDataTypeException extends RuntimeException {
    public SortableDataInvalidDataTypeException(String message) {
        super(message);
    }
}
