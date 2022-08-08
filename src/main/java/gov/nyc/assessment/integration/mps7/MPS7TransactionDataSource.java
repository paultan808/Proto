package gov.nyc.assessment.integration.mps7;

import gov.nyc.assessment.domain.*;
import gov.nyc.assessment.integration.ProtoIntegrationException;
import gov.nyc.assessment.integration.TransactionDataSource;

import java.io.DataInput;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class MPS7TransactionDataSource implements TransactionDataSource {
    private final DataInput dataInput;
    private final int fileVersion;
    private final List<Transaction> transactionList;

    public MPS7TransactionDataSource(DataInput dataInput) throws ProtoIntegrationException {
        this.dataInput = dataInput;

        try {
            if (dataInput.readByte() == 'M' &&
                    dataInput.readByte() == 'P' &&
                    dataInput.readByte() == 'S' &&
                    dataInput.readByte() == '7'
            ) {
                fileVersion = dataInput.readByte();
                long recordCount = Integer.toUnsignedLong(dataInput.readInt());

                transactionList = new ArrayList<Transaction>(recordCount > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) recordCount);

                while (recordCount-- > 0) {
                    TransactionType txType;
                    long timeStamp;
                    String userId;
                    double amount = 0;

                    Transaction tx = null;

                    switch (dataInput.readByte()) {
                        case 0x00:
                            txType = TransactionType.Debit;
                            break;
                        case 0x01:
                            txType = TransactionType.Credit;
                            break;
                        case 0x02:
                            txType = TransactionType.StartAutopay;
                            break;
                        case 0x03:
                            txType = TransactionType.EndAutopay;
                            break;
                        default:
                            throw new ProtoIntegrationException("Unexpected data in file");
                    }

                    timeStamp = Integer.toUnsignedLong(dataInput.readInt());
                    userId = Long.toUnsignedString(dataInput.readLong());

                    if (txType == TransactionType.Debit || txType == TransactionType.Credit) {
                        amount = dataInput.readDouble();
                    }

                    if (userId != null && userId.length() > 0 && timeStamp > 0) {
                        UserId nUserId = new UserId(userId);
                        LocalDateTime nTimeStamp = LocalDateTime.ofEpochSecond(timeStamp, 0, ZoneOffset.UTC);

                        switch (txType) {
                            case Debit:
                                tx = new DebitTransaction(nUserId, nTimeStamp, amount);
                                break;
                            case Credit:
                                tx = new CreditTransaction(nUserId, nTimeStamp, amount);
                                break;
                            case StartAutopay:
                                tx = new StartAutopayTransaction(nUserId, nTimeStamp);
                                break;
                            case EndAutopay:
                                tx = new EndAutopayTransaction(nUserId, nTimeStamp);
                                break;
                        }

                        transactionList.add(tx);
                    } else {
                        throw new ProtoIntegrationException("Data corruption in file");
                    }
                }
            } else {
                throw new ProtoIntegrationException("Unrecognized file format");
            }
        } catch (IOException e) {
            throw new ProtoIntegrationException(e);
        }
    }

    @Override
    public List<Transaction> getAllRecords() {
        return transactionList;
    }
}
