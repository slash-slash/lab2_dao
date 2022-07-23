package com.company.query;

import com.company.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Query03 {
    // Сделайте выборку Издателей и измените имя определенного Издателя
    public static void main(String[] args) {
        try(Connection con = ConnectionFactory.getConnection()) {
            Statement statement = con.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name");
            String name = scanner.nextLine();
            System.out.println("Enter new name");
            String newName = scanner.nextLine();

            String query = "UPDATE Publishers SET publisherName='" + newName + "' WHERE publisherName like '" + name + "'";
            int result = statement.executeUpdate(query);
            if (result == 0) {
                throw new SQLException("nothing changed, name not found");
            }
        } catch (SQLException e) {
            if (e.getMessage() == null) {
                e.printStackTrace();
            } else {
                System.err.println(e.getMessage());
            }
        }
    }
}
