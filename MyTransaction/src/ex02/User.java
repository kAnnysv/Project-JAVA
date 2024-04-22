package ex02;

import ex01.UserIdsGenerator;

public class User {

    private String name;
    private Integer id;

    public User(String name){
        this.name = name;
        this.id = UserIdsGenerator.getInstance().generatedId();
    }

    public Integer getId() {
        return id;
    }

    @Override

    public  String toString(){

        return "name " + name + "  id " + id + "\n";

    }




}

