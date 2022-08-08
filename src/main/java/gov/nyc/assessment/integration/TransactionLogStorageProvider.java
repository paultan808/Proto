package gov.nyc.assessment.integration;

import java.io.DataInput;

public interface TransactionLogStorageProvider {
    DataInput getDataInput() throws ProtoStorageProviderException;
}

