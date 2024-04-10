package ex05;

import ex04.Transaction;
import ex04.TransactionService;
import ex04.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionService transactionService = new TransactionService();

    public void printMenu(){

        int command = 0;
        while (command < 7) {
        System.out.println("1. Add a user\n" +
                "2. View user balances\n" +
                "3. Perform a transfer\n" +
                "4. View all transactions for a specific user\n" +
                "5. DEV – remove a transfer by ID\n" +
                "6. DEV – check transfer validity\n" +
                "7. Finish execution\n");


            Scanner scanner = new Scanner(System.in);
            command = scanner.nextInt();
            if(command == 1){
                addUser();
            }else if(command == 3){
                performTransfer();

            } else if (command == 2) {
                viewUserBalance();

            } else if (command == 4) {
                viewAllTransactionsUser();

            } else if (command == 5) {
                removeTransferByID();

            } else if (command == 6) {
                checkTransferValidity();
            }


        }
    }
    private void addUser(){
        System.out.println("Enter a user name and a balance");
        Scanner scanner = new Scanner(System.in);
        String userBalance = scanner.nextLine();
        String[] tmp = userBalance.split(" ");
        User user = new User(tmp[0],Double.parseDouble(tmp[1]));
        transactionService.addUser(user);
        System.out.println("User with " + "id = " + user.getId() + " is added");
        System.out.println("======================================");



    }
    private void performTransfer(){
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        Scanner scanner = new Scanner(System.in);
        String userBalance = scanner.nextLine();
        String[] tmp = userBalance.split(" ");

        int senderId = Integer.parseInt(tmp[0]);
        int recipient = Integer.parseInt(tmp[1]);
        double amount = Double.parseDouble(tmp[2]);
        transactionService.processTransaction(transactionService.getUser(senderId).getIdentifier(),
                transactionService.getUser(recipient).getIdentifier(), amount);
        System.out.println("The transfer is completed");
        System.out.println("======================================");
    }

    private void viewUserBalance(){
        System.out.println("Enter a user ID");
        Scanner scanner = new Scanner(System.in);
        String userBalance = scanner.nextLine();
        String[] tmp = userBalance.split(" ");
        int userId = Integer.parseInt(tmp[0]);
        System.out.println(transactionService.getUser(userId).getName() + "-" + transactionService.getUser(userId).getBalance());
        System.out.println("======================================");


    }

    private void viewAllTransactionsUser(){
        System.out.println("Enter a user ID");
        Scanner scanner = new Scanner(System.in);
        String userBalance = scanner.nextLine();
        String[] tmp = userBalance.split(" ");
        int userId = Integer.parseInt(tmp[0]);
        User user = transactionService.getUser(userId);
        user.getTransactionLinkedList().printEx05();
        System.out.println("======================================");


    }
    public void removeTransferByID(){
        System.out.println("Enter a user ID and a transfer ID");
        Scanner scanner = new Scanner(System.in);
        String userBalance = scanner.nextLine();
        String[] tmp = userBalance.split(" ");
        int userId = Integer.parseInt(tmp[0]);

        String trId = tmp[1];
        transactionService.getUser(userId).getTransactionLinkedList().deleteTransactionEx05(trId);


    }

    public void checkTransferValidity(){
        System.out.println("Enter a user1 ID and user2 ID");
        Scanner scanner = new Scanner(System.in);
        String userBalance = scanner.nextLine();
        String[] tmp = userBalance.split(" ");
        int userId1 = Integer.parseInt(tmp[0]);
        int userId2 = Integer.parseInt(tmp[1]);
        List<Transaction> tm = new ArrayList<>();
        tm = transactionService.correctTransaction(transactionService.getUser(userId1).getIdentifier(), transactionService.getUser(userId2).getIdentifier());
        for(Transaction tr :  tm){
            System.out.println(tr.getUserName() + "(id = " + " has an unacknowledged transfer id = " + tr.getIdentifier() + " amount " + tr.getAmount());
        }

    }






}
