package ewansheldon.kata.bank_extended;

public class BankAccount {
    private TransactionRepository transactionRepository;

    public BankAccount(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void deposit(int amount) {
        transactionRepository.addTransaction(amount);
    }

    public void withdraw(int amount) {
        transactionRepository.addTransaction(-amount);
    }

    public void printStatement() {
        throw new UnsupportedOperationException();
    }
}
