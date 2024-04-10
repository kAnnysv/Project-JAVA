package ex04;



import java.util.UUID;

public interface UserList {

    public void addAUser(User user);
    public User retrieveAUserByID(UUID id);
    public User retrieveAUserByIndex(Integer index);
    public Integer retrieveTheNumberOfUsers();

}
