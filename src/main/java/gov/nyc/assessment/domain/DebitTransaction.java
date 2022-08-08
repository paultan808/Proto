package gov.nyc.assessment.domain;

import java.time.LocalDateTime;

public final class DebitTransaction extends TransactionWithAmount {
    public DebitTransaction(UserId userId, LocalDateTime transactionTimestamp, double amount) {
        super(TransactionType.Debit, userId, transactionTimestamp, amount);
    }
}
