package gov.nyc.assessment.service;

import gov.nyc.assessment.domain.Transaction;
import gov.nyc.assessment.domain.TransactionType;
import gov.nyc.assessment.domain.UserId;

import java.util.List;

public interface TransactionLogRepository {
    List<Transaction> getAllTransactions();
    List<Transaction> getAllTransactionsOfTypes(TransactionType[] transactionTypes);
    List<Transaction> getAllTransactionsForUser(UserId userId);
    List<Transaction> getAllTransactionsOfTypesForUser(UserId userId, TransactionType[] transactionTypes);
}

