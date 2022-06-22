package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Liza", "Silchuk", (byte) 19);
        userService.saveUser("Vania", "Military", (byte) 20);
        userService.saveUser("Vlada", "Ivanovna", (byte) 5);
        userService.saveUser("Ivan", "Ivanovich", (byte) 3);
        System.out.println("Все users в бд:");
        userService.getAllUsers();
        userService.removeUserById(3);
        System.out.println("Все users в бд после удаления:");
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.stopSF();


    }
}
