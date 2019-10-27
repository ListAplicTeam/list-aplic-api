package br.com.ufg.listaplic.exception;

public class NetworkException extends RuntimeException {

    public NetworkException(String msg) {
        super(msg);
    }

    public NetworkException(String msg, Exception e) {
        super(msg, e);
    }

}
