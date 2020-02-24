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
    void add_deposit_to_transaction_repository() {
        int amount = 1000;

        bankAccount.deposit(amount);
        verify(transactionRepository).addTransaction(amount);
    }

    @Test
    void add_withdrawal_to_transaction_repository() {
        int amount = 1000;

        bankAccount.withdraw(amount);
        verify(transactionRepository).addTransaction(-amount);
    }

    @Test
    void send_transactions_to_print_statement() {
        List<Transaction> transactions = createTransactions(500, -100);
        given(transactionRepository.getAll()).willReturn(transactions);
        bankAccount.printStatement();

        verify(statementPrinter).print(transactions);
    }

    @Test
    void transfer_to_another_account() {
        TransactionRepository targetTransactionRepository = mock(TransactionRepository.class);
        BankAccount targetAccount = new BankAccount(targetTransactionRepository, statementPrinter);
        bankAccount.transfer(1000, targetAccount);

        verify(transactionRepository).addTransaction(-1000);
        verify(targetTransactionRepository).addTransaction(1000);
    }

    private List<Transaction> createTransactions(int ...amounts) {
        List<Transaction> transactions = new ArrayList<>();
        for (int amount : amounts) {
            transactions.add(new Transaction(amount, "02/02/2020"));
        }

        return transactions;
    }
}