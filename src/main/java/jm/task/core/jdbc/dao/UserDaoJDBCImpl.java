package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    public UserDaoJDBCImpl() {
         connection = Util.getConnection();
    }

    public void createUsersTable() {
       execute("create table users\n" +
                "(\n" +
                "    id        int auto_increment,\n" +
                "    name      VARCHAR(20) not null,\n" +
                "    lastName VARCHAR(20) null,\n" +
                "    age       int(3)      null,\n" +
                "    constraint users_pk\n" +
                "        primary key (id)\n" +
                ");");

    }

    public void dropUsersTable() {
      execute("drop table users");

    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = connection.createStatement();){
            statement.executeUpdate("insert into users (name, lastName, age) values ('"
            + name + "','" + lastName + "','" + age + "')");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    public void removeUserById(long id) {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("delete from users where id='"
            + id + "')");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try(Statement statement = connection.createStatement();){
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()){
                result.add(new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4)));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement();){
            statement.executeUpdate("delete from users");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    private void execute(String sql) {
        try(Statement statement = connection.createStatement();){
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
