package com.gtsp.gtsp.exception;

public class GtspException extends RuntimeException {
	
	public GtspException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public GtspException(String exMessage) {
        super(exMessage);
    }

}
