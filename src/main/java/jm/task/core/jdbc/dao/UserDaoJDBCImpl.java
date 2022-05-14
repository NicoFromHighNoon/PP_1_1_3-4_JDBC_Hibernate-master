package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE `testdb`.`users` ( " +
                    "  `id` INT NOT NULL AUTO_INCREMENT, " +
                    "  `name` VARCHAR(45) NULL, " +
                    "  `lastName` VARCHAR(45) NULL, " +
                    "  `age` INT NULL, " +
                    "  PRIMARY KEY (`id`), " +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC)); ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  users (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM  users where id=" + id + "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> user = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                user.add(new User(set.getInt("id"),
                        set.getString("name"),
                        set.getString("lastName"),
                        set.getByte("age"))
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users ");
            System.out.println("Таблица очищена");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
