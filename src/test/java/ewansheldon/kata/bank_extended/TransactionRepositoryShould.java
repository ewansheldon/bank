package ewansheldon.kata.bank_extended;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryShould {
    private TransactionRepository transactionRepository = new TransactionRepository();

    @Test
    void create_transaction_and_store() {
        Transaction transaction = new Transaction(1000);
        transactionRepository.addTransaction(1000);
        List<Transaction> transactions = transactionRepository.getAll();

        assertEquals(1, transactions.size());
        assertEquals(transaction, transactions.get(0));
    }
}