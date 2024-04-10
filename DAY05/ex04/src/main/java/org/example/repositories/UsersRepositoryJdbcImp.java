package org.example.repositories;

import org.example.models.ChatRoom;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsersRepositoryJdbcImp implements UsersRepository{

    private Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/chat", "postgres", "123");
    private List<User> listUser = new ArrayList<>();

    public UsersRepositoryJdbcImp() throws SQLException {
    }

    public List<User> getListUser() {
        return listUser;
    }

    @Override
    public List<User> findAll(int page, int size) {
        int end = page * size;
        int start = end - size;

        try(PreparedStatement statement = connection.prepareStatement(SQLMessage.SELECT_USER_PAGE.QUERY)){
            statement.setInt(1, size);
            statement.setInt(2,start);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Long userId = resultSet.getLong(1);
                String userLogin = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                Long chatRoomId = resultSet.getLong(4);
                String chatName = resultSet.getString(5);
                HashMap<Long, String> owner = new HashMap<>();
                owner.put(chatRoomId, chatName);


                listUser.add(new User(userId,userLogin, userPassword, owner , null));

            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }



    enum SQLMessage{

        SELECT_USER_PAGE("select u.id, u.login, u.password, cr.id, cr.name\n" +
                "from users u\n" +
                "join chatrooms cr ON cr.owner = u.id\n" +
                "limit (?) offset(?)"
        );
        String QUERY;

        SQLMessage(String QUERY){
            this.QUERY = QUERY;
        }

    }
}
