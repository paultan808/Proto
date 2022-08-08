package gov.nyc.assessment.domain;

import java.time.LocalDateTime;

public final class CreditTransaction extends TransactionWithAmount {
    public CreditTransaction(UserId userId, LocalDateTime transactionTimestamp, double amount) {
        super(TransactionType.Credit, userId, transactionTimestamp, amount);
    }
}
