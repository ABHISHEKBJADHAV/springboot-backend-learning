package com.example.SpringBootJdbc.service;

import com.example.SpringBootJdbc.dao.StudentRepo;
import com.example.SpringBootJdbc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepo repo;

    public StudentRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(StudentRepo repo) {
        this.repo = repo;
    }

    public void addStudent(Student s) {
        repo.save(s);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
