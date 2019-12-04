package br.com.ufg.listaplic.exception;

public class ListApplicationFinishedException extends RuntimeException {

    public ListApplicationFinishedException() {
        super("List is already finished");
    }

}
