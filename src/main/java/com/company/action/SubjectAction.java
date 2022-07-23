package com.company.action;

import com.company.dao.SubjectDAO;
import com.company.model.Subject;

import java.util.List;

public class SubjectAction {
    SubjectDAO dao = new SubjectDAO();
    int st;

    public void insert(Subject subject) {
        st = dao.insert(subject);
        if (st == 1) {
            System.out.println("Subject Inserted Successfully");
        } else if (st == -1) {
            System.out.println("Subject Already exists");
        } else {
            System.out.println("Unable to Insert Subject");
        }
    }

    public void delete(Long id) {
        st = dao.delete(id);
        if (st == 1) {
            System.out.println("Subject Deleted Successfully");
        } else {
            System.out.println("No Record Found");
        }
    }

    public void fetchAll() {
        List<Subject> subjectList = dao.fetchAllSubjects();
        if (subjectList.isEmpty()) {
            System.out.println("No Record Found");
        } else {
            System.out.println("Subject Details are :");
            for (Subject subject : subjectList) {
                System.out.println(subject);
            }
        }
    }
}
