package com.company.query;
import com.company.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Query06 {
    // Обновите Имя автора по определенному id
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose author id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Choose new author name:");
        String authorName = scanner.nextLine();
        String query = "UPDATE Authors SET firstName = '" + authorName + "' WHERE authorID = " + id + ";";

        try (Connection con = ConnectionFactory.getConnection()) {
            Statement statement = con.createStatement();
            int status = statement.executeUpdate(query);
            if (status == 0){
                throw new SQLException("Can't update author: ID not found");
            }
            System.out.println("Operation completed");
        } catch (SQLException e) {
            if (e.getMessage() == null) {
                e.printStackTrace();
            } else {
                System.err.println(e.getMessage());
            }
        }
    }
}
