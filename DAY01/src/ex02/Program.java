package ex02;

import ex01.User;

public class Program {

    public static void main(String[] args) {
        UserArrayList db = new UserArrayList();

        System.out.println(db.getArrSize());


        for (int i = 0; i < 12; i++) {
            db.addAUser( new ex02.User("user" + i));
        }

        System.out.println(db.getArrSize());

        System.out.println(db.retrieveAUserByIndex(3));

        System.out.println(db.retrieveAUserByID(11));

        System.out.println(db.retrieveTheNumberOfUsers());



    }
}
