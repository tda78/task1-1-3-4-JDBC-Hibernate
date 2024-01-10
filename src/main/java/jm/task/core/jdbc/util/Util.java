package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection;
    private static final String URL  = "jdbc:mysql://localhost:3306/myusers";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        try{
            connection = DriverManager.getConnection(URL, DB_USER,DB_PASSWORD);
        }catch (Exception e){
            System.out.println("no connection! Exception!!");
        }
        return connection;
    }
}
