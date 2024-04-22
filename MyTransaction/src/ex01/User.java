package ex01;

public class User {

    private String name;
    private Integer id;

    public User(String name){
        this.name = name;
        this.id = UserIdsGenerator.getInstance().generatedId();
    }
    @Override

    public  String toString(){

        return "name " + name + "  id " + id + "\n";

    }




}
