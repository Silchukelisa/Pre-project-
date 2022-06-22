package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;

public class UserServiceImpl implements UserService {

    private static SessionFactory sessionFactory;
    private UserDaoJDBCImpl daoJDBC;
    private UserDaoHibernateImpl daoHibernate;
    //private final Connection connection;
    Configuration configuration;



    public UserServiceImpl() {
        //connection = Util.getMysqlConnection();
        configuration= Util.getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    //создать таблицу пользователей
    public void createUsersTable() throws SQLException {
        //daoJDBC = new UserDaoJDBCImpl(connection);
        //daoJDBC.createUsersTable();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        daoHibernate=new UserDaoHibernateImpl(session);
        daoHibernate.createUsersTable();
        transaction.commit();
        session.close();
    }

    //удалить таблицу пользователей
    public void dropUsersTable() throws SQLException {
        //daoJDBC = new UserDaoJDBCImpl(connection);
        //daoJDBC.dropUsersTable();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        daoHibernate=new UserDaoHibernateImpl(session);
        daoHibernate.dropUsersTable();
        transaction.commit();
        session.close();
    }

    //сохранить пользователя
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        //daoJDBC = new UserDaoJDBCImpl(connection);
        //daoJDBC.saveUser(name, lastName, age);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        daoHibernate=new UserDaoHibernateImpl(session);
        daoHibernate.saveUser(name, lastName, age);
        transaction.commit();
        session.close();

    }

    //удалить идентификатор пользователя
    public void removeUserById(long id) throws SQLException {
        //daoJDBC = new UserDaoJDBCImpl(connection);
        //daoJDBC.removeUserById(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        daoHibernate=new UserDaoHibernateImpl(session);
        daoHibernate.removeUserById(id);
        transaction.commit();
        session.close();
    }

    //получить всех пользователей
    @Transactional
    public List<User> getAllUsers() throws SQLException {
        List<User> users;
        //daoJDBC = new UserDaoJDBCImpl(connection);
        //users = daoJDBC.getAllUsers();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        daoHibernate=new UserDaoHibernateImpl(session);
        users=daoHibernate.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        transaction.commit();
        session.close();
        return users;
    }

    //очистить таблицу пользователей
    public void cleanUsersTable() throws SQLException {
        //daoJDBC = new UserDaoJDBCImpl(connection);
        //daoJDBC.cleanUsersTable();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        daoHibernate=new UserDaoHibernateImpl(session);
        daoHibernate.cleanUsersTable();
        transaction.commit();
        session.close();
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);

    }

    public void stopSF() {
        sessionFactory.close();
    }
}
