package br.com.ufg.listaplic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NoOneStudentOnClassroomException extends RuntimeException {

	public NoOneStudentOnClassroomException() {
		super("There's no student enrollment on this classroom");
	}

}
