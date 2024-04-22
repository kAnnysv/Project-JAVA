package ex04;



import java.util.UUID;

public class Transaction {

    private UUID identifier;
    private User user;

    private TransferCategory category;
    private Double amount;


    public enum TransferCategory{
        DEBITS, CREDITS
    }

    public Transaction(User user,  Double amount, UUID identifier, TransferCategory category){

            this.user = user;
            this.amount = amount > 0 ? amount : - amount;
            this.identifier = identifier;
            this.category = category;


            if(category.equals(TransferCategory.CREDITS)){
                user.setBalance(user.getBalance() - this.amount);
            }else if(category.equals(TransferCategory.DEBITS)){
                user.setBalance(user.getBalance() + this.amount);
            }else {
                throw new IllegalTransactionException();
            }
            user.setTransactionLinkedList(this);

    }

    public UUID getIdentifier(){
        return identifier;
    }

    public TransferCategory getCategory() {
        return category;
    }
    public Double getAmount() {
        if(this.category == TransferCategory.CREDITS){
            return -amount;
        }

        return amount;
    }

    public String getUserName() {
        return user.getName();
    }
    public Integer getUserId(){
        return user.getId();
    }


    @Override
    public String toString(){
        return "Tr " + this.identifier + "\n"
                + "Amount " + this.amount + "\n"
                + "Category " + category + "\n"
                + "User  " + user.getName() + " " + user.getIdentifier() +  "\n";



    }











}

