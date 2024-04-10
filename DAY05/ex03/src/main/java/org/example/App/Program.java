package org.example.App;

import org.example.models.ChatRoom;
import org.example.models.Message;
import org.example.models.User;
import org.example.repositories.MessageRepositoryJdbcImpl;
import org.example.repositories.MessagesRepository;


import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class Program {
    public static void main(String[] args) throws SQLException {



        MessagesRepository messagesRepository = new MessageRepositoryJdbcImpl();
        Optional<Message> messageOptional = messagesRepository.finById(11L);
        if(messageOptional.isPresent()){
            Message message = messageOptional.get();
            message.setMessageText("Bay");
            message.setDateTime(Timestamp.valueOf(LocalDateTime.now()));
            messagesRepository.update(message);


        }





    }

}
