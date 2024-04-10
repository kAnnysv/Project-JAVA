package ex03;

import ex00.Transaction;

import java.util.UUID;

public interface TransactionList {

    public void addTransaction(Transaction transaction);
    public void deleteTransaction(UUID id);
    public Transaction[] toArray();

}
