import ewansheldon.kata.bank_extended.BankAccount;
import ewansheldon.kata.bank_extended.Console;
import ewansheldon.kata.bank_extended.StatementPrinter;
import ewansheldon.kata.bank_extended.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class AccountPrintStatementFeature {
    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        TransactionRepository transactionRepository = new TransactionRepository();
        StatementPrinter statementPrinter = new StatementPrinter();
        bankAccount = new BankAccount(transactionRepository, statementPrinter);
    }

    @Test
    void should_print_transactions_to_console_in_chronological_order() {
        Console console = mock(Console.class);
        bankAccount.deposit(1000);
        bankAccount.withdraw(100);
        bankAccount.deposit(500);
        bankAccount.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printStatement("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printStatement("01/04/2014 | 1000.00 | 1000.00");
        inOrder.verify(console).printStatement("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printStatement("10/04/2014 | 500.00 | 1400.00");
    }
}
