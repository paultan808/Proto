package gov.nyc.assessment.domain;

import java.time.LocalDateTime;

public final class StartAutopayTransaction extends Transaction {
    public StartAutopayTransaction(UserId userId, LocalDateTime transactionTimestamp) {
        super(TransactionType.StartAutopay, userId, transactionTimestamp);
    }
}
