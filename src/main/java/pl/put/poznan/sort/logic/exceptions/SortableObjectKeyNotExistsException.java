package pl.put.poznan.sort.logic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SortableObjectKeyNotExistsException extends RuntimeException {
    public SortableObjectKeyNotExistsException(String message) {
        super(message);
    }
}
