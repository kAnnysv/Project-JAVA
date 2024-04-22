package org.example.App;

import org.example.repositories.MessageRepositoryJdbcImpl;


import java.sql.SQLException;

public class Program {
    public static void main(String[] args) throws SQLException {

        MessageRepositoryJdbcImpl repositoryJdbc = new MessageRepositoryJdbcImpl();
        System.out.println(repositoryJdbc.finById(4L));
    }

}
