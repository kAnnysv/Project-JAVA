package org.example.repositories;



import org.example.models.ChatRoom;
import org.example.models.Message;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class MessageRepositoryJdbcImpl implements MessagesRepository{

    private List<Message> repoMessage = new ArrayList<>();
    private Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/chat", "postgres", "123");

    public MessageRepositoryJdbcImpl() throws SQLException {
    }

    private void createRepo() throws SQLException {


        try (PreparedStatement statement = connection.prepareStatement("select m.id, u.id, u.login, u.password, cr.id, cr.name, m.text, m.datetime \n" +
                "from messages m\n" +
                "\tjoin users u on u.id = m.author\n" +
                "\tjoin chatrooms cr on cr.id = m.room\n")){
            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                int messId = resultSet.getInt(1);
                Long userId = resultSet.getLong(2);
                String loginUser = resultSet.getString(3);
                String passwUser = resultSet.getString(4);
                User user1 = new User(userId, loginUser, passwUser);

                Long roomId = resultSet.getLong(5);
                String roomName = resultSet.getString(6);
                String text = resultSet.getString(7);
                Time dateTime = resultSet.getTime(8);
                ChatRoom chatRoom = new ChatRoom(roomId, roomName,loginUser);
                Message message = new Message(messId, user1, chatRoom, text, dateTime);
                repoMessage.add(message);


            }

        }

    }
    @Override
    public Optional<Message> finById(Long id) {
        try {
            createRepo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Message message : repoMessage){
            if(message.getId() == id){
                return Optional.of(message);
            }
        }


        return Optional.empty();
    }
}
