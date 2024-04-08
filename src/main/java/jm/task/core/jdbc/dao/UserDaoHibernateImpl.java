package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                id BIGSERIAL PRIMARY KEY,
                name VARCHAR(128) NOT NULL ,
                lastName VARCHAR(128) NOT NULL ,
                age SMALLINT NOT NULL )
                                
                """;
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createNativeQuery(sql, User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = """
                DROP TABLE IF EXISTS users
                """;
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createNativeQuery(sql, User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User();

        try (Session session = Util.getSession()) {
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = Util.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = Util.getSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();

            return users;
        }
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = Util.getSession()) {

            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
