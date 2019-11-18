package br.com.ufg.listaplic.exception;

import br.com.ufg.listaplic.exception.response.FieldError;
import br.com.ufg.listaplic.exception.response.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);
    private static final String AN_ERROR_OCCURRED = "An error occurred, {}";

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ResponseError> handleResourceNotFoundException(final ResourceNotFoundException e) {
        LOGGER.error(AN_ERROR_OCCURRED, e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError(e.getMessage()));
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<ResponseError> handleInvalidPasswordException(final InvalidPasswordException e) {
        LOGGER.error(AN_ERROR_OCCURRED, e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseError(e.getMessage()));
    }

    @ExceptionHandler(StudentIsAlreadyEnrolledException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<ResponseError> handleStudentIsAlreadyEnrolledException(final StudentIsAlreadyEnrolledException e) {
        LOGGER.error(AN_ERROR_OCCURRED, e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseError(e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseError> handleIllegalArgumentException(final IllegalArgumentException e) {
        LOGGER.error(AN_ERROR_OCCURRED, e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleValidationExceptions(MethodArgumentNotValidException e) {
        List<FieldError> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            Object rejectedValue = ((org.springframework.validation.FieldError) error).getRejectedValue();
            String errorMessage = error.getDefaultMessage();
            errors.add(new FieldError(fieldName, errorMessage, rejectedValue));
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError(errors));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseError> handleUncaughtException(Exception e) {
        LOGGER.error(AN_ERROR_OCCURRED, e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseError(e.getMessage()));
    }

}
