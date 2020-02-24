import ewansheldon.kata.bank_extended.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class AccountFeatures {
    private BankAccount bankAccount;
    private Console console;
    private Clock clock;

    @BeforeEach
    void setUp() {
        clock = mock(Clock.class);
        console = mock(Console.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        StatementPrinter statementPrinter = new StatementPrinter(console);
        bankAccount = new BankAccount(transactionRepository, statementPrinter);
    }

    @Test
    void should_print_transactions_to_console_in_chronological_order() {
        given(clock.todayAsString()).willReturn(
                "01/04/2014", "02/04/2014", "10/04/2014"
        );
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

    @Test
    void should_transfer_money_from_one_account_to_another() {
        given(clock.todayAsString()).willReturn(
                "01/04/2014", "03/04/2014", "03/04/2014"
        );
        BankAccount targetAccount = createBankAccount();
        bankAccount.deposit(1000);
        bankAccount.transfer(1000, targetAccount);

        bankAccount.printStatement();
        InOrder inOrder = inOrder(console);
        inOrder.verify(console).print("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).print("01/04/2014 | 1000.00 | 1000.00");
        inOrder.verify(console).print("03/04/2014 | -1000.00 | 0.00");

        targetAccount.printStatement();
        inOrder.verify(console).print("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).print("03/04/2014 | 1000.00 | 1000.00");
    }

    private BankAccount createBankAccount() {
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        StatementPrinter statementPrinter = new StatementPrinter(console);
        return new BankAccount(transactionRepository, statementPrinter);
    }
}
