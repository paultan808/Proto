package gov.nyc.assessment.integration;

public class ProtoStorageProviderException extends Exception {
    public ProtoStorageProviderException() {
        super();
    }

    public ProtoStorageProviderException(String message) {
        super(message);
    }

    public ProtoStorageProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProtoStorageProviderException(Throwable cause) {
        super(cause);
    }

    protected ProtoStorageProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
