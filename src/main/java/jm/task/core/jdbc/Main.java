package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Lev", "Filippov", (byte) 23);
        userService.saveUser("Oleg", "Petrov", (byte) 21);
        userService.saveUser("Maks", "Ivanov", (byte) 35);
        userService.saveUser("Olga", "Makarova", (byte) 27);
        System.out.println(userService.getAllUsers());
    }
}