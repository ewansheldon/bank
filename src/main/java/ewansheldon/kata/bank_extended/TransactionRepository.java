package ewansheldon.kata.bank_extended;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionRepository {
    private List<Transaction> transactions;
    private Clock clock;

    public TransactionRepository(Clock clock) {
        transactions = new ArrayList<>();
        this.clock = clock;
    }

    public void addTransaction(int amount) {
        transactions.add(new Transaction(amount, todayAsString()));
    }

    private String todayAsString() {
        return clock.todayAsString();
    }

    public List<Transaction> getAll() {
        return transactions;
    }

    public List<Transaction> getFiltered(Map<String, String> filter) {
        throw new UnsupportedOperationException();
    }
}
