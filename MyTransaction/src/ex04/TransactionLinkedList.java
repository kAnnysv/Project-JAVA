package ex04;



import ex03.TransactionNotFoundException;

import java.util.UUID;

public class TransactionLinkedList  {

    private Node head;
    private Node tail;
    private Integer count = 0;

    public TransactionLinkedList(){
        this.head = null;
        this.tail = null;
    }

    public Integer getCount() {
        return count;
    }

    private boolean isEmpty(){
        return head == null;
    }
    public void print(){
        Node tmp = head;

        while (tmp != null){
            System.out.println(tmp.transaction);
            tmp = tmp.next;
        }

    }

    public void printEx05(){
        Node tmp = head;

        while (tmp != null){
            System.out.println("To " + tmp.transaction.getUserName() + "(id = " + tmp.transaction.getUserId() + ") "
            + tmp.transaction.getAmount() + " with id = " + tmp.transaction.getIdentifier());
            tmp = tmp.next;
        }





    }

    public void deleteFirst(){

        if(head.next == null){
            tail = null;

        }else {
            head.next.prev = null;
        }
        head = head.next;
        count--;
    }
    public void deleteLast(){

        if(tail.prev == null){
            head = null;
        }else{
            tail.prev.next = null;
        }
        tail = tail.prev;
        count--;
    }





    public void addTransaction(Transaction transaction) {
        Node tmp = new Node(transaction);
        if(isEmpty()){
            tail = tmp;
        }else{
            head.prev = tmp;
        }
        tmp.next = head;
        head = tmp;
        count++;
    }


    public void deleteTransaction(UUID id) {
        Node cur = head;

        while (cur.getTransaction().getIdentifier() != id){
            cur = cur.next;
            if(cur == null) throw new TransactionNotFoundException("Транзакция с идентификатором " + id + " не найдена.");
        }
        if(cur.equals(head)) {
            deleteFirst();
        }else if(cur.equals(tail)){
            deleteLast();
        }else {
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }

        count--;

    }

    public void deleteTransactionEx05(String id) {
        Node cur = head;

        while (!(cur.getTransaction().getIdentifier().toString().equals(id))){
            cur = cur.next;
            if(cur == null) throw new TransactionNotFoundException("Транзакция с идентификатором " + id + " не найдена.");
        }
        if(cur.equals(head)) {
            deleteFirst();
        }else if(cur.equals(tail)){
            deleteLast();
        }else {
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }

        count--;

    }




    public Transaction[] toArray() {
        Transaction[] arr = new Transaction[count];
        int i = 0;
        Node tmp = head;
        while (tmp != null){
            arr[i] = tmp.getTransaction();
            tmp = tmp.next;
            i++;

        }
        return arr;
    }


    private class Node {
        Transaction transaction;
        Node next;
        Node prev;
        public Node(Transaction transaction){
            this.transaction = transaction;

        }

        public Transaction getTransaction() {
            return transaction;
        }


    }





}
