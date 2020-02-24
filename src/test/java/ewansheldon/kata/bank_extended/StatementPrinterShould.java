package ewansheldon.kata.bank_extended;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class StatementPrinterShould {
    private StatementPrinter statementPrinter;
    private Console console;

    @BeforeEach
    void setUp() {
        console = mock(Console.class);
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    void always_print_the_header() {
        statementPrinter.print(emptyList());
        verify(console).print("DATE | AMOUNT | BALANCE");
    }

    @Test
    void print_given_transaction_with_amount_date_and_running_total() {
        Transaction deposit = mock(Transaction.class);
        given(deposit.getAmount()).willReturn(1000);
        given(deposit.getDate()).willReturn("12/10/2019");

        Transaction withdrawal = mock(Transaction.class);
        given(withdrawal.getAmount()).willReturn(-300);
        given(withdrawal.getDate()).willReturn("17/10/2019");

        List<Transaction> transactions = List.of(deposit, withdrawal);

        statementPrinter.print(transactions);
        verify(console).print("DATE | AMOUNT | BALANCE");
        verify(console).print("12/10/2019 | 1000 | 1000");
        verify(console).print("17/10/2019 | -300 | 700");
    }
}