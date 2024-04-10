package ex04;




import ex01.UserIdsGenerator;

import java.util.UUID;

public class User {
    private  Integer id;
    private UUID identifier;
    private String name;
    private Double balance;
    private TransactionLinkedList transactionLinkedList;
    public User(String name, Double balance){

        if(balance < 0){
            System.out.println("incorrect balance");
        }else {

            this.identifier = UUID.randomUUID();
            this.name = name;
            this.balance = balance;
            this.transactionLinkedList = new TransactionLinkedList();
            this.id = UserIdsGenerator.getInstance().generatedId();


        }

    }

    public void setTransactionLinkedList(Transaction transaction) {
        this.transactionLinkedList.addTransaction(transaction);
    }



    public Integer getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public TransactionLinkedList getTransactionLinkedList() {
        return transactionLinkedList;
    }

    @Override
    public String toString() {
        return "User - " + this.identifier + "\n"
            + "Name - " + this.name + "\n"
            + "Balance - " + this.balance + "\n"
            + "Id " + this.getId() + "\n";

    }

}
