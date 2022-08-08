package gov.nyc.assessment.integration;

public class ProtoIntegrationException extends Exception {
    public ProtoIntegrationException() {
        super();
    }

    public ProtoIntegrationException(String message) {
        super(message);
    }

    public ProtoIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProtoIntegrationException(Throwable cause) {
        super(cause);
    }

    protected ProtoIntegrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}