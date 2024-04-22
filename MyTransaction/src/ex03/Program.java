package ex03;

import ex00.Transaction;
import ex00.User;

public class Program {
    public static void main(String[] args) {

        User user1 = new User("Vasy", 900.00);
        User user2 = new User("Pit", 400.00);


        Transaction transaction = new Transaction(user2,user1, Transaction.TransferCategory.CREDITS, 300.00);
        Transaction transaction1 = new Transaction(user1,user2, Transaction.TransferCategory.CREDITS, 200.00);
        Transaction transaction2 = new Transaction(user1,user2, Transaction.TransferCategory.CREDITS, 100.00);

        TransactionLinkedList sber = new TransactionLinkedList();
        sber.addTransaction(transaction);
        sber.addTransaction(transaction1);
        sber.addTransaction(transaction2);

        sber.deleteTransaction(transaction1.getIdentifier());

        Transaction[] arr = sber.toArray();
        for(Object o : arr){
            System.out.println(o);
        }

        Transaction[] arr1 = user1.getTransactionLinkedList().toArray();
        for(Object o : arr1){
            System.out.println("user -  " + o);
        }






        sber.print();
        System.out.println("COUNT " + sber.getCount());



    }

}
