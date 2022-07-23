package com.company.query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.company.dao.ConnectionFactory;

// Сделайте выборку по авторам, отсортировав по их Имени и Фамилии
public class Query01 {
    public static void main(String[] args) {
        String query = "SELECT * FROM Authors ORDER BY firstName, lastName";

        try(Connection con = ConnectionFactory.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");

                int id = resultSet.getInt("authorId");

                System.out.println(id + "\t" + firstName + "\t" + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
