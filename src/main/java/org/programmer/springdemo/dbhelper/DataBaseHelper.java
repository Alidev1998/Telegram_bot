package org.programmer.springdemo.dbhelper;

import org.programmer.springdemo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper {

    private Connection connection;
    private Statement statement;

    public DataBaseHelper() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\JAVA\\Projects\\src\\main\\resources\\static\\User.db");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean insertData(User user) {
        String sql = "INSERT INTO Users(firstname,lastname,age,phone,password) " +
                "VALUES('" + user.getFirstName() + "','" + user.getLastName() + "'," + user.getAge() + ",'" + user.getPhone() + "','" + user.getPassword() + "')";
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<User> users() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM Users";
            ResultSet resultSet = null;

            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user1 = new User();
                user1.setId(resultSet.getInt("id"));
                user1.setFirstName(resultSet.getString("firstname"));
                user1.setLastName(resultSet.getString("lastname"));
                user1.setAge(resultSet.getInt("age"));
                user1.setPhone(resultSet.getString("phone"));
                user1.setPassword(resultSet.getString("password"));
                users.add(user1);
            }
            resultSet.close();
        } catch (SQLException e) {
        }
        return users;
    }

    public User getOne(int id) {
        String query = "SELECT * FROM Users WHERE id=" + id + "";
        ResultSet resultSet = null;
        User user1 = new User();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                user1.setId(resultSet.getInt("id"));
                user1.setFirstName(resultSet.getString("firstname"));
                user1.setLastName(resultSet.getString("lastname"));
                user1.setAge(resultSet.getInt("age"));
                user1.setPhone(resultSet.getString("phone"));
                user1.setPassword(resultSet.getString("password"));
            }
            resultSet.close();
        } catch (SQLException e) {
        }
        return user1;
    }

    public boolean update(User user, int id){
        String query = "UPDATE Users SET firstname='"+user.getFirstName()+"',lastname='"+
                user.getLastName()+"',age="+user.getAge()+",phone='"+
                user.getPhone()+"',password='"+user.getPassword()+"' WHERE id="+id+"";
        try {
            statement.executeUpdate(query);
            connection.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public int getUserId(String phone){
        int id = 0;
        String query = "SELECT * FROM Users WHERE phone='" + phone + "'";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            id = resultSet.getInt("id");
            resultSet.close();
        } catch (SQLException e) {
        }
        return id;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM Users WHERE id = " + id + "";
        try {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void closeConnections(){
        try {
            if (connection != null){
                connection = null;
                connection.close();
            }
            if (statement != null){
                statement = null;
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
