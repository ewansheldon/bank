package ewansheldon.kata.bank_extended;

import java.util.Map;

public class BankAccount {
    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    public BankAccount(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        transactionRepository.addTransaction(amount);
    }

    public void withdraw(int amount) {
        transactionRepository.addTransaction(-amount);
    }

    public void printStatement() {
        statementPrinter.print(transactionRepository.getAll());
    }

    public void printStatement(Map<String, String> filter) {
        statementPrinter.print(transactionRepository.getAll());
    }

    public void transfer(int amount, BankAccount otherAccount) {
        this.withdraw(amount);
        otherAccount.deposit(amount);
    }
}
