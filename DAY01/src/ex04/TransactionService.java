package ex04;

import ex02.UserNotFoundException;


import java.util.*;

public class TransactionService {
    private ListUsers listUsers = new ListUsers();
    private UUID idTransaction;
    public TransactionService(){
        this.idTransaction = UUID.randomUUID();
    }

    public void processTransaction(UUID sender,UUID recipient, Double amount){

        User send = listUsers.retrieveAUserByID(sender);
        User recip = listUsers.retrieveAUserByID(recipient);
        if (send.getBalance() < amount){
            throw new IllegalTransactionException();
        }
        Double tmp = send.getBalance();
        new Transaction(send, amount,idTransaction, Transaction.TransferCategory.CREDITS);
        if(tmp - send.getBalance() != Math.abs(amount)){
            send.setBalance(tmp);
            throw new IllegalTransactionException();
        }
        new Transaction(recip, amount, idTransaction, Transaction.TransferCategory.DEBITS);

    }
    public void printListUsers(){
        for (User u: listUsers.getArr()) System.out.println(u);
    }




    public Double getUserBalance(UUID userId){

        for(User user : listUsers.getArr()){
            if (user.getIdentifier().equals(userId)) {
                return user.getBalance();
            }
        }
        throw new UserNotFoundException("Пользователь с идентификатором " + userId + " не найден.");

    }

    public void addUser(User user){
        listUsers.addAUser(user);
    }
    public User getUser(Integer id){
        for(User user : listUsers.getArr()){
            if (user.getId() == id) {

                return user;

            }
        }
        throw new UserNotFoundException("Пользователь с идентификатором " + id + " не найден.");

    }

    public void accountStatement(UUID userId){

        for(User user : listUsers.getArr()){
            if (user.getIdentifier().equals(userId)) {
            user.getTransactionLinkedList().print();

            }
        }
        throw new UserNotFoundException("Пользователь с идентификатором " + userId + " не найден.");


    }
    public void deleteTransaction(UUID userId, UUID idTransaction){
        for(User user : listUsers.getArr()){
            if (user.getIdentifier().equals(userId)) {
                user.getTransactionLinkedList().deleteTransaction(idTransaction);
                return;

            }
        }
        throw new UserNotFoundException("Пользователь с идентификатором " + userId + " не найден.");

    }

    public void deleteTransactionEx05(Integer userId, String idTransaction){
        for(User user : listUsers.getArr()){
            if (user.getId().equals(userId)) {
                user.getTransactionLinkedList().deleteTransactionEx05(idTransaction);
                return;

            }
        }
        throw new UserNotFoundException("Пользователь с идентификатором " + userId + " не найден.");

    }

    public List<Transaction> correctTransaction(UUID user1, UUID user2){




        Transaction[] arr1 = listUsers.retrieveAUserByID(user1).getTransactionLinkedList().toArray();
        Transaction[] arr2 = listUsers.retrieveAUserByID(user2).getTransactionLinkedList().toArray();
        if(Arrays.equals(arr1, arr2)) return null;
        arr1 = arr1.length > arr2.length ? arr1 : arr2;
        arr2 = arr1.length > arr2.length ? arr2 : arr1;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if(arr1[i].getIdentifier().equals(arr2[j].getIdentifier())){
                    arr1[i] = null;
                    //System.out.println(arr1[i]);
                    arr2[j] = null;
                }

            }

        }

        List <Transaction> tmp = new ArrayList<>(Arrays.asList(arr1));
        tmp.addAll(Arrays.asList(arr2));

        tmp.removeIf(Objects::isNull);
        
        return tmp;
    }


}
