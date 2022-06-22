package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao dao;

    public UserServiceImpl() {
        //dao = new UserDaoJDBCImpl();
        dao=new UserDaoHibernateImpl();
    }

    //создать таблицу пользователей
    public void createUsersTable() throws SQLException {
        dao.createUsersTable();
    }

    //удалить таблицу пользователей
    public void dropUsersTable() throws SQLException {
        dao.dropUsersTable();
    }

    //сохранить пользователя
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        dao.saveUser(name, lastName, age);
    }

    //удалить идентификатор пользователя
    public void removeUserById(long id) throws SQLException {
        dao.removeUserById(id);
    }

    //получить всех пользователей
    public List<User> getAllUsers() throws SQLException {
        List<User> users;
        users=dao.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        return users;
    }

    //очистить таблицу пользователей
    public void cleanUsersTable() throws SQLException {
        dao.cleanUsersTable();
    }

    public void stop() {
        dao.stop();
    }

}
