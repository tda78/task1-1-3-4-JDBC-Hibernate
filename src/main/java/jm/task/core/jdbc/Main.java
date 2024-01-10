package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();

        service.createUsersTable();
        User user1 = new User("qwe","rty", (byte) 12);
        service.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
        System.out.println("User с именем – " + user1.getName() + " добавлен в базу данных");

        User user2 = new User("asd","fgh", (byte) 23);
        service.saveUser(user2.getName(),user2.getLastName(),user2.getAge());
        System.out.println("User с именем – " + user2.getName() + " добавлен в базу данных");

        User user3 = new User("zxc","vbn", (byte) 34);
        service.saveUser(user3.getName(),user3.getLastName(),user3.getAge());
        System.out.println("User с именем – " + user3.getName() + " добавлен в базу данных");

        User user4 = new User("qaz","yhn", (byte) 45);
        service.saveUser(user4.getName(),user4.getLastName(),user4.getAge());
        System.out.println("User с именем – " + user4.getName() + " добавлен в базу данных");

        List<User> users = service.getAllUsers();
        for (User user:users) {
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
