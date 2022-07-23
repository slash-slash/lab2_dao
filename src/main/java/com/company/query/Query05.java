package com.company.query;
import com.company.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// Выполните добавление Нового автора в БД
public class Query05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter author first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter author last name:");
        String lastName = scanner.nextLine();

        try(Connection con = ConnectionFactory.getConnection()) {
            Statement statement = con.createStatement();
            String query = "INSERT INTO Authors(FIRSTNAME, LASTNAME) " +
                    "VALUES ('" + firstName + "','" + lastName + "')";
            int num = statement.executeUpdate(query);
            if(num == 0){
                throw new SQLException("Can't create author");
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
