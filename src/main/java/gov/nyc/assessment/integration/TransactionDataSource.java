package gov.nyc.assessment.integration;

import gov.nyc.assessment.domain.Transaction;

import java.util.List;

public interface TransactionDataSource {
    List<Transaction> getAllRecords();
}

