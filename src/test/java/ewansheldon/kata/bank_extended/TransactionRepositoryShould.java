package ewansheldon.kata.bank_extended;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TransactionRepositoryShould {
    private Clock clock = mock(Clock.class);
    private TransactionRepository transactionRepository = new TransactionRepository(clock);

    @Test
    void create_transaction_and_store() {
        String date = "01/01/2020";
        Transaction transaction = new Transaction(1000, date);
        given(clock.todayAsString()).willReturn(date);
        transactionRepository.addTransaction(1000);
        List<Transaction> transactions = transactionRepository.getAll();

        assertEquals(1, transactions.size());
        assertEquals(transaction, transactions.get(0));
    }
}