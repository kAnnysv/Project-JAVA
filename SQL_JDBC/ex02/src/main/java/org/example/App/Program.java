package org.example.App;

import org.example.models.ChatRoom;
import org.example.models.Message;
import org.example.models.User;
import org.example.repositories.MessageRepositoryJdbcImpl;


import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Program {
    public static void main(String[] args) throws SQLException {
        User user = new User(0l, "----01", "tt");
        ChatRoom chatRoom = new ChatRoom(0L, "frtg-------|||||", user);
        String text = ")))))))))))))))OOOOOOOOOOOOOO";

        Message message =new Message(0L, user, chatRoom, text, Timestamp.valueOf(LocalDateTime.now()));


        MessageRepositoryJdbcImpl repositoryJdbc = new MessageRepositoryJdbcImpl();

        repositoryJdbc.save(message);


        System.out.println("Message ID = " + repositoryJdbc.getRepoMessage().size());


    }

}
