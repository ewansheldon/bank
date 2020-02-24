package ewansheldon.kata.bank_extended;

import java.text.DecimalFormat;
import java.util.List;

public class StatementPrinter {
    public static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";
    public static final String STATEMENT_FORMAT = "%s | %s | %s";
    public static final String MONEY_FORMAT = "#.00";
    private Console console;
    private int balance;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        console.print(STATEMENT_HEADER);
        formatTransactions(transactions);
    }

    private void formatTransactions(List<Transaction> transactions) {
        balance = 0;
        for (Transaction transaction : transactions) {
            console.print(formatTransaction(transaction));
        }
    }

    private String formatTransaction(Transaction transaction) {
        return String.format(STATEMENT_FORMAT, transaction.getDate(),
                moneyFormat(transaction.getAmount()), moneyFormat(this.balance += transaction.getAmount()));
    }

    private String moneyFormat(int amount) {
        return new DecimalFormat(MONEY_FORMAT).format(amount);
    }
}
