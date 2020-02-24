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
    private Console console;

    @BeforeEach
    void setUp() {
        console = mock(Console.class);
        TransactionRepository transactionRepository = new TransactionRepository();
        StatementPrinter statementPrinter = new StatementPrinter(console);
        bankAccount = new BankAccount(transactionRepository, statementPrinter);
    }

    @Test
    void should_print_transactions_to_console_in_chronological_order() {
        bankAccount.deposit(1000);
        bankAccount.withdraw(100);
        bankAccount.deposit(500);
        bankAccount.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).print("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).print("01/04/2014 | 1000.00 | 1000.00");
        inOrder.verify(console).print("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).print("10/04/2014 | 500.00 | 1400.00");
    }
}
