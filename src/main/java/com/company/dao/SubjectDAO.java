package com.company.dao;

import com.company.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int st;//status

    public int insert(Subject subject) {
        con = ConnectionFactory.getConnection();
        try {
            String query = "insert into subject(name,capacity) "
                    + "values(?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, subject.getName());
            ps.setInt(2, subject.getCapacity());
            st = ps.executeUpdate();
            System.out.println("inserted subject " + st);
        } catch (SQLIntegrityConstraintViolationException e) {
            st = -1;
            e.printStackTrace();
        } catch (Exception e) {
            st = -2;
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return st;
    }

    public int delete(long id) {
        con = ConnectionFactory.getConnection();
        try {
            String query = "delete from subject where id=? ";
            ps = con.prepareStatement(query);
            ps.setLong(1, id);
            st = ps.executeUpdate();
            System.out.println("deleted subject " + st);
        } catch (Exception e) {
            st = -2;
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return st;
    }

    public List<Subject> fetchAllSubjects() {
        List<Subject> subjectList = new ArrayList<Subject>();

        con = ConnectionFactory.getConnection();
        try {
            String query = "select * from subject";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getLong("id"));
                subject.setName(rs.getString("name"));
                subject.setCapacity(rs.getInt("capacity"));
                subjectList.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return subjectList;
    }
}
