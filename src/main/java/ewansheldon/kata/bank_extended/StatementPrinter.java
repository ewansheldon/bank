package ewansheldon.kata.bank_extended;

import java.util.List;

public class StatementPrinter {
    private Console console;
    private int balance;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        console.print("DATE | AMOUNT | BALANCE");
        formatTransactions(transactions);
    }

    private void formatTransactions(List<Transaction> transactions) {
        balance = 0;
        for (Transaction transaction : transactions) {
            console.print(formatTransaction(transaction));
        }
    }

    private String formatTransaction(Transaction transaction) {
        return String.format("%s | %s | %s", transaction.getDate(), transaction.getAmount(), this.balance += transaction.getAmount());
    }
}
