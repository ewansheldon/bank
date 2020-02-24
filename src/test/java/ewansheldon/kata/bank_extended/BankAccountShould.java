package ewansheldon.kata.bank_extended;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BankAccountShould {
    private BankAccount bankAccount;
    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        statementPrinter = mock(StatementPrinter.class);
        bankAccount = new BankAccount(transactionRepository, statementPrinter);
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

    @Test
    void should_send_transactions_to_print_statement() {
        List<Transaction> transactions = createTransactions(500, -100);
        given(transactionRepository.getAll()).willReturn(transactions);
        bankAccount.printStatement();

        verify(statementPrinter).print(transactions);
    }

    private List<Transaction> createTransactions(int ...amounts) {
        List<Transaction> transactions = new ArrayList<>();
        for (int amount : amounts) {
            transactions.add(new Transaction(amount));
        }

        return transactions;
    }
}