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


    public List<Message> getRepoMessage() {
        return repoMessage;
    }

    private List<Message> repoMessage = new ArrayList<>();
    private Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/chat", "postgres", "123");
   
    public MessageRepositoryJdbcImpl() throws SQLException {
    }

    public void createRepo() throws SQLException {


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
                Timestamp dateTime = resultSet.getTimestamp(8);
                ChatRoom chatRoom = new ChatRoom(roomId, roomName, user1);
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

    @Override
    public void save(Message message) {
        saveUsers(message.getAuthor());
        saveChatRoom(message.getChatRoom());
        try(PreparedStatement statement = connection.prepareStatement(SQLMessage.INSERT_MESSAGE.QUERY)){
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getChatRoom().getId());
            statement.setString(3, message.getMessageText());
            statement.setTimestamp(4, message.getDateTime());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int messageId = resultSet.getInt(1);
            message.setId(Long.valueOf(messageId));
            createRepo();


        } catch (SQLException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        }


    }

    @Override
    public void update(Message message) {
        validation(message);
        try(PreparedStatement statement = connection.prepareStatement(SQLMessage.UPDATE_MESSAGES.QUERY)){
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getChatRoom().getId());
            statement.setString(3, message.getMessageText());
            statement.setTimestamp(4, message.getDateTime());
            statement.setLong(5, message.getId());
            createRepo();
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println("Update massage id = " + resultSet.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private void validation(Message message){
        boolean userIs = false;
        boolean chatRoomIs = false;
        boolean messageIs = false;
        for(Message messageList : repoMessage){
            if (messageList.getAuthor().getId() == message.getAuthor().getId() ) userIs = true;
            if (messageList.getChatRoom().getId() == message.getChatRoom().getId()) chatRoomIs = true;
            if (messageList.getId() == message.getId()) messageIs = true;

        }
        if(userIs && chatRoomIs && messageIs){
            return;
        }else {
            throw new NotSavedSubEntityException("not id in bd" + " user = " + userIs + " chatRoom = " + chatRoomIs + " message = " + messageIs);
        }

    }

    public void saveUsers(User user){

        try(PreparedStatement statement = connection.prepareStatement(SQLMessage.INSERT_USER.QUERY)){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

           ResultSet res = statement.executeQuery();
           res.next();
           int userId = res.getInt(1);
            user.setId(Long.valueOf(userId));



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void saveChatRoom(ChatRoom chatRoom){
        try(PreparedStatement statement = connection.prepareStatement(SQLMessage.INSERT_CHATROOM.QUERY)){
            statement.setString(1, chatRoom.getChatName());
            statement.setLong(2, chatRoom.getChatOwner().getId());

            ResultSet res = statement.executeQuery();
            res.next();
            int chatRoomId = res.getInt(1);
            chatRoom.setId(Long.valueOf(chatRoomId));



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    enum SQLMessage{
        INSERT_USER("INSERT INTO users ( id, login, password) VALUES (DEFAULT, (?), (?)) RETURNING id"),
        INSERT_CHATROOM("INSERT INTO chatrooms ( id, name, owner) VALUES (DEFAULT, (?), (?)) RETURNING id"),
        INSERT_MESSAGE("INSERT INTO messages ( id, author, room, text, datetime) VALUES (DEFAULT, (?), (?), (?), (?)) RETURNING id"),
        UPDATE_MESSAGES("UPDATE messages SET author = (?), room = (?), text = (?), datetime = (?) WHERE id = (?) RETURNING id");



        String QUERY;
        SQLMessage(String QUERY){
            this.QUERY = QUERY;
        }

    }
}
