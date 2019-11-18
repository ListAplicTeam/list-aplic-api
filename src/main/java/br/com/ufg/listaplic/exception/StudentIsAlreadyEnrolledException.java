package br.com.ufg.listaplic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudentIsAlreadyEnrolledException extends RuntimeException {

    public StudentIsAlreadyEnrolledException() {
        super("Student is already enrolled");
    }

}
