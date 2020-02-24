package ewansheldon.kata.bank_extended;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BankAccountShould {
    private BankAccount bankAccount;
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        bankAccount = new BankAccount(transactionRepository);
    }

    @Test
    void should_add_deposit_to_transaction_repository() {
        int amount = 1000;

        bankAccount.deposit(amount);
        verify(transactionRepository).addTransaction(amount);
    }

    @Test
    void should_add_withdrawal_to_transaction_repository() {
        int amount = 1000;

        bankAccount.withdraw(amount);
        verify(transactionRepository).addTransaction(-amount);
    }
}