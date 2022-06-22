package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getMysqlConnection();
    }

    //создать таблицу пользователей
    public void createUsersTable() throws SQLException {
        String sql="CREATE TABLE IF NOT EXISTS User (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20),LastName VARCHAR(20), Age INT)";
        PreparedStatement stmt =  connection.prepareStatement(sql);
        stmt.execute(sql);
        stmt.close();
    }

    //удалить таблицу пользователей
    public void dropUsersTable() throws SQLException {
        String sql="DROP TABLE IF EXISTS User";
        PreparedStatement stmt =  connection.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }

    //сохранить пользователя
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql="INSERT User (Name,LastName,Age) VALUES ('" + name + "','" + lastName + "'," + age + ")";
        PreparedStatement stmt =  connection.prepareStatement(sql);
        System.out.println("User с именем - " + name + " добавлен в базу данных");
        stmt.executeUpdate();
        stmt.close();
    }

    //удалить идентификатор пользователя
    public void removeUserById(long id) throws SQLException {
        String sql="DELETE FROM User WHERE Id ="+id;
        PreparedStatement stmt =  connection.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();

    }

    //получить всех пользователей
    public List<User> getAllUsers() throws SQLException {
        List<User> users=new ArrayList<>();
        String sql="SELECT * FROM User";
        PreparedStatement stmt =  connection.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next()){
            String name = resultSet.getString("Name");
            String lastName= resultSet.getString("LastName");
            byte age = resultSet.getByte("Age");
            users.add(new User(name,lastName,age));

        }
        stmt.close();
        return users;
    }

    //очистить таблицу пользователей
    public void cleanUsersTable() throws SQLException {
        String sql="DELETE FROM User";
        PreparedStatement stmt =  connection.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }

    public void stop() {
    }
}
