package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getConnection() {

        Connection connection = ConnectionManager.open();
        return connection;
    }
}