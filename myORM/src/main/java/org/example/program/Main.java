package org.example.program;

import org.example.model.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    //private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        OrmManager manager = new OrmManager();
        manager.createTables();
        Object user = new User(1L, "Wasy2", "Jonovich", 52);
        manager.save(user);
        Object user2 = new User(1L, "Wasy2000000000", "Jono77777777", 52);
        manager.update(user2);
        Class<?> clazz = User.class;
        System.out.println(manager.findById(1L, clazz));


    }
}
