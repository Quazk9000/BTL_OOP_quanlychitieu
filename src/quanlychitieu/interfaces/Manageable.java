package quanlychitieu.interfaces;

import quanlychitieu.model.Transaction;
import java.util.List;

public interface Manageable {
    void add(Transaction t);
    void remove(int id);
    void update(int id, Transaction t);
    List<Transaction> search(String keyword);
}