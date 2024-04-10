package ex04;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Sigizmund", 1000.00);
        User user2 = new User("Columbina", 3000.00);


        TransactionService transactionService = new TransactionService();

        transactionService.addUser(user1);
        transactionService.addUser(user2);
        transactionService.printListUsers();

        transactionService.processTransaction(user1.getIdentifier(), user2.getIdentifier(), 100.00);
        user1.getTransactionLinkedList().print();
        user2.getTransactionLinkedList().print();
        System.out.println("==================================================");
        // Создаем не парную транзакцию user1
        Transaction transactionNotPair = new Transaction(user1, 200.00, UUID.randomUUID(), Transaction.TransferCategory.CREDITS);


        List<Transaction> notPairTransaction = transactionService.correctTransaction(user1.getIdentifier(), user2.getIdentifier());
        for(Transaction t : notPairTransaction){
            System.out.println("Не парная транзакция " + t);
        }

        transactionService.deleteTransaction(user1.getIdentifier(), transactionNotPair.getIdentifier());
        user1.getTransactionLinkedList().print();

        System.out.println("========== Непарная транзакция удалена ===========");
        user1.getTransactionLinkedList().print();




    }
}
