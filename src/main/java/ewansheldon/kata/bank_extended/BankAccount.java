package ewansheldon.kata.bank_extended;

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
}
