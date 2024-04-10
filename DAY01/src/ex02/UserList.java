package ex02;

public interface UserList {

    public void addAUser(User user);
    public User retrieveAUserByID(Integer id);
    public User retrieveAUserByIndex(Integer index);
    public Integer retrieveTheNumberOfUsers();




}
