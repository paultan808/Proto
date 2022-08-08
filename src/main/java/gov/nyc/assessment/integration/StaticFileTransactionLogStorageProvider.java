package gov.nyc.assessment.integration;

import java.io.DataInput;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class StaticFileTransactionLogStorageProvider implements TransactionLogStorageProvider {
    private static final String DEMO_STATIC_FILE_NAME = "txnlog.dat";

    @Override
    public DataInput getDataInput() throws ProtoStorageProviderException {
        try {
            return new RandomAccessFile(DEMO_STATIC_FILE_NAME, "r");
        } catch (FileNotFoundException e) {
            throw new ProtoStorageProviderException("File " + DEMO_STATIC_FILE_NAME + " could not be found", e);
        }
    }
}

