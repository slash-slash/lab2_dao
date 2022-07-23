package com.company.query;
import com.company.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// Добавить новую Titles (При передачи VALUES publisherID – нужно сделать подзапросом select*from publisher where publisherName =””)
public class Query08 {
    public static void main(String[] args) {
        try(Connection con = ConnectionFactory.getConnection()) {
            Statement statement = con.createStatement();
            String isbn, title, editionNumber, year, publisherID, price;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter isbn:");
            isbn = scanner.nextLine();
            System.out.println("Enter title:");
            title = scanner.nextLine();
            System.out.println("Enter edition number:");
            editionNumber = scanner.nextLine();
            System.out.println("Enter year:");
            year = scanner.nextLine();
            System.out.println("Enter publisher name:");
            publisherID = getPublisherID(scanner.nextLine(), statement);
            System.out.println("Enter price:");
            price = scanner.nextLine();

            String query = "INSERT INTO  Titles(isbn, title, editionNumber, year, publisherID, price) VALUE ('" + isbn + "','" + title + "','" + editionNumber + "','" + year + "','" + publisherID + "','" + price + "');";
            int result = statement.executeUpdate(query);
            if (result == 0){
                throw new SQLException("Ops, something going wrong!");
            }
            System.out.println("Operation complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getPublisherID(String publisherName, Statement statement) throws SQLException {
        String result = "";
        String query = "SELECT * FROM Publishers WHERE publisherName = '" + publisherName + "';";
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            result = resultSet.getString(1);
        }
        return result;
    }
}
