package ex00;

import java.util.UUID;

public class Transaction {

    private UUID identifier;
    private User recipient;
    private User sender;
    private TransferCategory category;
    private Double amount;

    public enum TransferCategory{
        DEBITS, CREDITS
    }

    public Transaction(User recipient, User sender, TransferCategory category, Double amount){
        if(validation(sender, amount, category)){
            System.out.println("Недостаточно средств");
            System.exit(1);

        }else {
            this.identifier = UUID.randomUUID();
            this.recipient = recipient;
            this.sender = sender;
            this.category = category;
            this.amount = amount;
            processTransaction(recipient, sender, amount);
            recipient.getTransactionLinkedList().addTransaction(this);
            sender.getTransactionLinkedList().addTransaction(this);
        }



    }

    public UUID getIdentifier(){
        return identifier;
    }
    public User getRecipient(){
        return recipient;
    }

    public User getSender() {
        return sender;
    }
    public TransferCategory getCategory() {
        return category;
    }
    public Double getAmount() {
        return amount;
    }

    private static boolean validation(User sender, Double amount, TransferCategory category){
        amount = amount < 0 ? -amount : amount;
        return sender.getBalance() < amount && category == TransferCategory.CREDITS;
    }

    public static void processTransaction(User sender, User recipient, Double amount){
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }
    @Override

    public String toString(){
        return "Tr " + this.identifier + "\n"
                + "Amount " + this.amount + "\n"
                + "Category CREDITS" + "\n"
                + "Sender " + sender.getName() + " " + sender.getIdentifier() +  "\n"
                + "Category DEBITS" + "\n"
                + "Recipient " + recipient.getName() + " " + recipient.getIdentifier() + "\n";
    }





}

