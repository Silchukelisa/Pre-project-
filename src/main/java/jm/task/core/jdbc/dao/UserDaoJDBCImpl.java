package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    private Statement stmt;


    public UserDaoJDBCImpl() {
    }

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    //создать таблицу пользователей
    public void createUsersTable() throws SQLException {
        stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20),LastName VARCHAR(20), Age INT)");
        stmt.close();
    }

    //удалить таблицу пользователей
    public void dropUsersTable() throws SQLException {
        stmt = connection.createStatement();
        stmt.execute("DROP TABLE IF EXISTS users");
    }

    //сохранить пользователя
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        stmt = connection.createStatement();
        stmt.execute("INSERT users (Name,LastName,Age) VALUES ('" + name + "','" + lastName + "'," + age + ")");
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    //удалить идентификатор пользователя
    public void removeUserById(long id) throws SQLException {
        stmt = connection.createStatement();
        stmt.execute("DELETE FROM users WHERE Id ="+id);
    }

    //получить всех пользователей
    public List<User> getAllUsers() throws SQLException {
        List<User> users=new ArrayList<>();
        stmt = connection.createStatement();
        stmt.execute("SELECT * FROM users");
        ResultSet resultSet = stmt.getResultSet();
        while(resultSet.next()){

            String name = resultSet.getString("Name");
            String lastName= resultSet.getString("LastName");
            byte age = resultSet.getByte("Age");

            users.add(new User(name,lastName,age));
        }
        return users;
    }

    //очистить таблицу пользователей
    public void cleanUsersTable() throws SQLException {
        stmt = connection.createStatement();
        stmt.execute("DELETE FROM users");
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
