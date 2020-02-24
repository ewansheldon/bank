package ewansheldon.kata.bank_extended;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private List<Transaction> transactions;

    public TransactionRepository() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(int amount) {
        transactions.add(new Transaction(amount));
    }

    public List<Transaction> getAll() {
        return transactions;
    }
}
