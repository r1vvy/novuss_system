package com.novuss.restfulservice.core.exception;

public class OutgoingAuthorizationServiceException extends RuntimeException {
        public OutgoingAuthorizationServiceException(String message) {
            super(message);
        }

        public OutgoingAuthorizationServiceException(String message, Throwable cause) {
            super(message, cause);
        }
}
