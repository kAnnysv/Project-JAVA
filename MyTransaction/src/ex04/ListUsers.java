package ex04;

import ex02.UserNotFoundException;



import java.util.Arrays;
import java.util.UUID;

public class ListUsers implements UserList {
    private static User[] arr = new User[10];
    private static Integer arrSize;

    public User[] getArr() {
        return arr;
    }

    @Override
    public void addAUser(User user) {
        if(arr[arr.length - 1] != null){
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        for (int i = 0; i < arr.length; i++) {

            if(arr[i] == null){
                arr[i] = user;
                return;
            }
        }


    }

    @Override
    public User retrieveAUserByID(UUID id) {
        for (int i = 0; i < arr.length; i++){
            User user = arr[i];
            if(user != null && user.getIdentifier() == id){
                return user;
            }
        }
        throw new UserNotFoundException("Пользователь с идентификатором " + id + " не найден.");
    }

    @Override
    public User retrieveAUserByIndex(Integer index) {
        if(index >= 0 && index < this.retrieveTheNumberOfUsers()){
            return arr[index];
        }else{
            throw new UserNotFoundException("Incorrect index");
        }
    }

    @Override
    public Integer retrieveTheNumberOfUsers() {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != null){
                count++;
            }else {
                break;
            }
        }

        return count;
    }

}


