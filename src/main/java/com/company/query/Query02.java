package com.company.query;

import com.company.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Query02 {
    // Добавьте нового Издателя (publusher)
    public static void main(String[] args) {
        Connection con = ConnectionFactory.getConnection();
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter publisher name:");
            String name = scanner.nextLine();
            Statement statement = con.createStatement();
            String query = "INSERT INTO Publishers (publisherName) VALUE ('" + name + "')";
            int result = statement.executeUpdate(query);

            if (result == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            if (e.getMessage() == null) {
                e.printStackTrace();
            } else {
                System.err.println(e.getMessage());
            }
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
