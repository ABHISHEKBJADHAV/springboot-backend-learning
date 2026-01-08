package com.example.Docker_Postgres_Demo.controller;

import com.example.Docker_Postgres_Demo.dao.StudentRepo;
import com.example.Docker_Postgres_Demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo repo;

    @GetMapping("/getStudents")
    public List<Student> getAllStudents()
    {
        return repo.findAll();
    }

    @PostMapping("addStudent")
    public Student addStudent(@RequestBody Student student)
    {
        return repo.save(student);
    }
}
