package com.company.query;
import com.company.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Предоставьте отсортированный список книг определенного издателя (при этом id требуемого издателя можно менять в sql запросе)
public class Query04 {
    private static void printTitles(List<String[]> titles) {
        int[] columnLength = new int[6];
        for (int i = 0; i < columnLength.length; i++) {
            int max = 0;
            for (String[] column : titles) {
                max = Math.max(column[i].length(), max);
            }
            columnLength[i] = max;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : columnLength) {
            sb.append("%").append(i + 2).append("s");
        }
        sb.append("\n");
        String str = sb.toString();
        int dashLineLength = 0;
        for (int i : columnLength) {
            dashLineLength += (i + 2);
        }

        StringBuilder dashLine = new StringBuilder();
        for (int i = 0; i < dashLineLength; i++) {
            dashLine.append("-");
        }
        System.out.println(dashLine);
        System.out.println("\t Titles");
        System.out.println(dashLine);
        System.out.println();

        for (String[] row : titles) {
            System.out.printf(str, row[0], row[1], row[2], row[3], row[4], row[5]);
        }
    }

    public static void main(String[] args) {
        try(Connection con = ConnectionFactory.getConnection()) {
            Statement statement = con.createStatement();
            System.out.println("Enter publisher ID:");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();

            String query = "SELECT * FROM Titles WHERE publisherID = " + id + ";";
            ResultSet resultSet = statement.executeQuery(query);

            List<String[]> titles = new ArrayList<>();
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                int editionNumber = resultSet.getInt("editionNumber");
                String year = resultSet.getString("year");
                int publisherID = resultSet.getInt("publisherID");
                double price = resultSet.getDouble("price");
                titles.add(new String[]{isbn, title, Integer.toString(editionNumber), year, Integer.toString(publisherID), Double.toString(price)});
            }
            if (titles.isEmpty()) {
                throw new SQLException("Can't complete operation: ID not found!");
            } else {
                printTitles(titles);
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
