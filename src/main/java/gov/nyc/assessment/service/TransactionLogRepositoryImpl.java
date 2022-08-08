package gov.nyc.assessment.service;

import gov.nyc.assessment.domain.Transaction;
import gov.nyc.assessment.domain.TransactionType;
import gov.nyc.assessment.domain.UserId;
import gov.nyc.assessment.integration.TransactionDataSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionLogRepositoryImpl implements TransactionLogRepository {
    private final TransactionDataSource repositoryDataSource;

    public TransactionLogRepositoryImpl(TransactionDataSource repositoryDataSource) {
        this.repositoryDataSource = repositoryDataSource;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return repositoryDataSource.getAllRecords();
    }

    @Override
    public List<Transaction> getAllTransactionsOfTypes(TransactionType[] transactionTypes) {
        return repositoryDataSource
                .getAllRecords()
                .stream()
                .filter(tran -> Arrays.stream(transactionTypes)
                        .anyMatch(type -> tran.getType() == type))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getAllTransactionsForUser(UserId userId) {
        return repositoryDataSource
                .getAllRecords()
                .stream()
                .filter(tran -> tran.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getAllTransactionsOfTypesForUser(UserId userId, TransactionType[] transactionTypes) {
        return repositoryDataSource
                .getAllRecords()
                .stream()
                .filter(tran -> tran.getUserId().equals(userId))
                .filter(tran -> Arrays.stream(transactionTypes)
                        .anyMatch(type -> tran.getType() == type))
                .collect(Collectors.toList());
    }
}
