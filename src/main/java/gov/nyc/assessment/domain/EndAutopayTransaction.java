package gov.nyc.assessment.domain;

import java.time.LocalDateTime;

public final class EndAutopayTransaction extends Transaction {
    public EndAutopayTransaction(UserId userId, LocalDateTime transactionTimestamp) {
        super(TransactionType.EndAutopay, userId, transactionTimestamp);
    }
}
