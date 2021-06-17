package com.DBSystelGmbH.CaseStudyMarkDurst.common.exceptions.exceptionHandler;

import com.DBSystelGmbH.CaseStudyMarkDurst.common.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity handleArbitraryRuntimeException(RuntimeException ex) {
        log.warn("BadRequestException occurred, message: {}", ex.getMessage());
        log.debug("Stacktrace: ", ex);
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity handleBadRequestException(BadRequestException ex) {
        log.warn("BadRequestException occurred, message: {}", ex.getMessage());
        log.debug("Stacktrace: ", ex);
        return new ResponseEntity("Unspecified Bad Request occurred", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex) {
        log.warn("EntityNotFoundException occurred, message: {}", ex.getMessage());
        log.debug("Stacktrace: ", ex);
        return new ResponseEntity("The requested resource can't be found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdMismatchException.class)
    public final ResponseEntity handleMismatchException(IdMismatchException ex) {
        log.warn("IdMismatchException occurred, message: {}", ex.getMessage());
        log.debug("Stacktrace: ", ex);
        return new ResponseEntity("Updating resource would lead to inconsistency", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotValidException.class)
    public final ResponseEntity handleIdNotValidException(IdNotValidException ex) {
        log.warn("IdNotValidException occurred, message: {}", ex.getMessage());
        log.debug("Stacktrace: ", ex);
        return new ResponseEntity("The Id of this value is not valid", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotUniqueException.class)
    public final ResponseEntity handleNotUniqueException(NotUniqueException ex) {
        log.warn("NotUniqueException occurred, message: {}", ex.getMessage());
        log.debug("Stacktrace: ", ex);
        return new ResponseEntity("Adding this resource would allow forbidden duplicates for some attributes", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PagedResultIsEmptyException.class)
    public final ResponseEntity handlePagedResultIsEmptyException(PagedResultIsEmptyException ex) {
        log.warn("PagedResultIsEmptyException occurred, message: {}", ex.getMessage());
        log.debug("Stacktrace: ", ex);
        return new ResponseEntity("There are no results", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdIsNullExceptionException.class)
    public final ResponseEntity handleIdIsNullException(IdIsNullExceptionException ex) {
        log.warn("IdIsNullException occurred, message: {}", ex.getMessage());
        log.debug("Stacktrace: ", ex);
        return new ResponseEntity("Id is null", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectIsNullException.class)
    public final ResponseEntity handleObjectIsNullException(ObjectIsNullException ex) {
        log.warn("ObjectIsNullException occurred, message: {}", ex.getMessage());
        log.debug("Stacktrace: ", ex);
        return new ResponseEntity("Object is null", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StringIsBlankException.class)
    public final ResponseEntity handleStringIsBlankException(StringIsBlankException ex) {
        log.warn("StringIsBlankException occurred, message: {}", ex.getMessage());
        log.debug("Stacktrace: ", ex);
        return new ResponseEntity("String is blank", HttpStatus.BAD_REQUEST);
    }
}
