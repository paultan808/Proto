package gov.nyc.assessment.domain;

import java.time.LocalDateTime;

public abstract class Transaction implements Comparable<Transaction> {
    private final TransactionType type;
    private final UserId userId;
    private final LocalDateTime transactionTimestamp;

    public Transaction(TransactionType type, UserId userId, LocalDateTime transactionTimestamp) {
        this.type = type;
        this.userId = userId;
        this.transactionTimestamp = transactionTimestamp;
    }

    public UserId getUserId() {
        return userId;
    }

    public LocalDateTime getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public int compareTo(Transaction o) {
        return transactionTimestamp.compareTo(o.transactionTimestamp);
    }
}
