package com.company.query;
import com.company.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// Добавить нового Publisher
public class Query07 {
    public static void main(String[] args) {
        try(Connection con = ConnectionFactory.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter publisher name:");
            String publisherName = scanner.nextLine();
            Statement statement = con.createStatement();
            String query = "INSERT INTO Publishers (publisherName) VALUE ('" +  publisherName +"');";
            statement.executeUpdate(query);
            System.out.println("Operation completed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
