package gov.nyc.assessment.domain;

import java.time.LocalDateTime;

public class TransactionWithAmount extends Transaction {
    protected final double amount;

    public TransactionWithAmount(TransactionType type, UserId userId, LocalDateTime transactionTimestamp, double amount) {
        super(type, userId, transactionTimestamp);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
