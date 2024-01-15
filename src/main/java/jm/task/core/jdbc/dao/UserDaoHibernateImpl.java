package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        executeSql("create table if not exists users\n" +
                "(\n" +
                "    id        int auto_increment,\n" +
                "    name      VARCHAR(20) not null,\n" +
                "    lastName VARCHAR(20) null,\n" +
                "    age       int(3)      null,\n" +
                "    constraint users_pk\n" +
                "        primary key (id)\n" +
                ");");
    }

    @Override
    public void dropUsersTable() {
        executeSql("drop table if exists users");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
              //  transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session=getSessionFactory().openSession();
        Transaction t=session.beginTransaction();

        User user=session.get(User.class, id);
        session.delete(user);
        t.commit();
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String stringQuery = "DELETE FROM User";
            Query query = session.createQuery(stringQuery);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private void executeSql(String sql) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createSQLQuery(sql).executeUpdate();

        transaction.commit();
        session.close();
    }
}
