package ex00;

public class Program {
    public static void main(String[] args) {

        User user1 = new User("Vasy", 900.00);
        User user2 = new User("Pit", 400.00);
        System.out.println(user1);
        System.out.println(user2);

        Transaction transaction = new Transaction(user2,user1, Transaction.TransferCategory.CREDITS, 300.00);
        System.out.println(transaction);

    }

}
