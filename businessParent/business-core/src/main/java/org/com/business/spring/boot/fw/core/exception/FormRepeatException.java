package org.com.business.spring.boot.fw.core.exception;

public class FormRepeatException extends RuntimeException {

	private static final long serialVersionUID = 1141670473626695454L;

	public FormRepeatException(String message) {
        super(message);
    }

    public FormRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}