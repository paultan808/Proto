package gov.nyc.assessment;

import gov.nyc.assessment.domain.*;
import gov.nyc.assessment.integration.*;
import gov.nyc.assessment.integration.mps7.MPS7TransactionDataSource;
import gov.nyc.assessment.service.TransactionLogRepository;
import gov.nyc.assessment.service.TransactionLogRepositoryImpl;

import java.util.stream.Collectors;

public class App
{

    public static void main(String[] args )
    {
        try {
            // These wire-ups would normally be provided by some type of DI container
            TransactionLogStorageProvider storageProvider = new StaticFileTransactionLogStorageProvider();
            TransactionDataSource dataSource = new MPS7TransactionDataSource(storageProvider.getDataInput());
            TransactionLogRepository repository = new TransactionLogRepositoryImpl(dataSource);

            App app = new App(repository);
            app.run();
        } catch (ProtoIntegrationException | ProtoStorageProviderException e) {
            e.printStackTrace();
        }

    }

    final TransactionLogRepository repository;
    final String DEMO_USER_ID = "2456938384156277127";

    private App(TransactionLogRepository repository) {
        this.repository = repository;
    }

    private void run() {

        double totalCredited = repository
                .getAllTransactionsOfTypes(new TransactionType[]{TransactionType.Credit})
                .stream()
                .mapToDouble(t -> ((CreditTransaction) t).getAmount())
                .sum();

        double totalDebited = repository
                .getAllTransactionsOfTypes(new TransactionType[]{TransactionType.Debit})
                .stream()
                .mapToDouble(t -> ((DebitTransaction) t).getAmount())
                .sum();

        double finalBalanceForUser = 0;

        for(TransactionWithAmount tAmt: repository
                .getAllTransactionsOfTypesForUser(new UserId(DEMO_USER_ID), new TransactionType[]{TransactionType.Debit, TransactionType.Credit})
                .stream()
                .sorted()
                .map(TransactionWithAmount.class::cast)
                .collect(Collectors.toList())) {
            if(tAmt.getType() == TransactionType.Debit) {
                finalBalanceForUser -= tAmt.getAmount();
            } else {
                finalBalanceForUser += tAmt.getAmount();
            }
        }

        int autopaysStarted = repository.getAllTransactionsOfTypes(new TransactionType[]{TransactionType.StartAutopay}).size();
        int autopaysEnded = repository.getAllTransactionsOfTypes(new TransactionType[]{TransactionType.EndAutopay}).size();

        System.out.printf("total credit amount=%.2f%n", totalCredited);
        System.out.printf("total debit amount=%.2f%n", totalDebited);
        System.out.printf("autopays started=%d%n", autopaysStarted);
        System.out.printf("autopays ended=%d%n", autopaysEnded);
        System.out.printf("balance for user %s=%.2f%n", DEMO_USER_ID, finalBalanceForUser);
    }
}

























