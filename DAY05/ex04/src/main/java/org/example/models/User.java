package org.example.models;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private HashMap<Long,String> createRoom;
    private List<ChatRoom> rooms;


    public User(Long id, String login, String password, HashMap<Long,String> createRoom, List<ChatRoom> rooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createRoom = createRoom;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(createRoom, user.createRoom) && Objects.equals(rooms, user.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createRoom=" + createRoom +
                ", rooms=" + rooms +
                '}';
    }
}
