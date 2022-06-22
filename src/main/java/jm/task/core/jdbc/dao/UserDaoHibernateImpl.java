package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Session session;


    public UserDaoHibernateImpl(Session session) {
        this.session = session;
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20),LastName VARCHAR(20), Age INT)";
        session.createSQLQuery(sql).executeUpdate();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        session.createSQLQuery(sql).executeUpdate();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT users (Name,LastName,Age) VALUES ('" + name + "','" + lastName + "'," + age + ")";
        session.createSQLQuery(sql).executeUpdate();
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE Id =" + id;
        session.createSQLQuery(sql).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        String sql = "select Name, LastName, Age from USERS";
        Query query = session.createSQLQuery(sql);
        List<Object[]> rows = query.list();
        List<User> users = new ArrayList<>();
        for (Object[] row : rows) {
            String name = row[0].toString();
            String lastName = row[1].toString();
            byte age = Byte.parseByte(row[2].toString());
            users.add(new User(name, lastName, age));
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        session.createSQLQuery(sql).executeUpdate();
    }

}
