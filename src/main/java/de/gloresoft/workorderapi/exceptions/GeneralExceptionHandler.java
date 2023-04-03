package de.gloresoft.workorderapi.exceptions;

import de.gloresoft.workorderapi.constants.ErrorCodes;
import de.gloresoft.workorderapi.entities.ErrorMessageEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e) {
        ErrorMessageEntity errorMessageEntity = new ErrorMessageEntity(e.getMessage(), "",
                ZonedDateTime.now(ZoneId.of("Z")), ErrorCodes.RESOURCE_NOT_FOUND);
        return new ResponseEntity<>(errorMessageEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<Object> handleNumberNotFound(NumberFormatException e) {
        ErrorMessageEntity errorMessageEntity = new ErrorMessageEntity(e.getMessage(), "The given id is not in a numeric format",
                ZonedDateTime.now(ZoneId.of("Z")), ErrorCodes.INCORRECT_NUMBER_FORMAT);
        return new ResponseEntity<>(errorMessageEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ResourceAlreadyExistsException.class})
    public ResponseEntity<Object> handleResourceAlreadyExists(ResourceAlreadyExistsException e) {
        ErrorMessageEntity errorMessageEntity = new ErrorMessageEntity(e.getMessage(), "The resource with the given id already exists. Please use a new id",
                ZonedDateTime.now(ZoneId.of("Z")), ErrorCodes.RESOURCE_ALREADY_EXISTS);
        return new ResponseEntity<>(errorMessageEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InsufficientBillaleDaysException.class})
    public ResponseEntity<Object> insufficientBillaleDaysException(ResourceAlreadyExistsException e) {
        ErrorMessageEntity errorMessageEntity = new ErrorMessageEntity(e.getMessage(), "There are not sifficient billable days available",
                ZonedDateTime.now(ZoneId.of("Z")), ErrorCodes.INSUFFICIENT_BILLABLE_DAYS);
        return new ResponseEntity<>(errorMessageEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DateNotParsedException.class})
    public ResponseEntity<Object> handleDateNotParsed(DateNotParsedException e) {
        ErrorMessageEntity errorMessageEntity = new ErrorMessageEntity(e.getMessage(), "The date of birth is not correct. Correct Format MM-DD-YYYY",
                ZonedDateTime.now(ZoneId.of("Z")), ErrorCodes.INCORRECT_DATE_FORMAT);
        return new ResponseEntity<>(errorMessageEntity, HttpStatus.BAD_REQUEST);
    }
}
