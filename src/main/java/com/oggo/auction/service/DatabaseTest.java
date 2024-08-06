package com.oggo.auction.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://project-db-cgi.smhrd.com:3307/campus_24SW_fullstack_hack_2";
        String username = "campus_24SW_fullstack_hack_2";
        String password = "smhrd2";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");

            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getString("user_id"));
                System.out.println("Password: " + resultSet.getString("password"));
                System.out.println("Nickname: " + resultSet.getString("nickname"));
                System.out.println("Likes: " + resultSet.getInt("likes"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
