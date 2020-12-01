package org.ilyushenko.lab4.dao;

import org.ilyushenko.lab4.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class UserDAO {
    private static Connection conn;

    static {
        String url = null;
        String username = null;
        String password = null;
        //load db properties
        try (InputStream in = UserDAO.class.getClassLoader().getResourceAsStream("persistens.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //acquire db connection
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public boolean getOne;

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("select * from users");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setName(rs.getString(1));
            user.setSurname(rs.getString(2));
            user.setSurname(rs.getString(3));
            users.add(user);
        }
        return users;
    }

    public User getOne(String email) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users where email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setName(rs.getString(1 ));
                user.setSurname(rs.getString(2 ));
                user.setEmail(rs.getString(3 ));
                return user;
            }
        } catch (SQLException igonred) {
        }
        return null;
    }}