package org.example.App;

import org.example.models.ChatRoom;
import org.example.models.Message;
import org.example.models.User;
import org.example.repositories.MessageRepositoryJdbcImpl;
import org.example.repositories.MessagesRepository;
import org.example.repositories.UsersRepository;
import org.example.repositories.UsersRepositoryJdbcImp;


import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class Program {
    public static void main(String[] args) throws SQLException {

        UsersRepositoryJdbcImp usersRepository = new UsersRepositoryJdbcImp();

        usersRepository.findAll(2,5);
        for(User user : usersRepository.getListUser()){
            System.out.println(user);
        }









    }

}
