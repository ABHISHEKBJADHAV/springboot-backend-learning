package com.example.SpringBootJdbc;

import com.example.SpringBootJdbc.model.Student;
import com.example.SpringBootJdbc.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringBootJdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootJdbcApplication.class, args);
		Student s1 = context.getBean(Student.class);
		s1.setRollNo(4);
		s1.setName("Nanasaheb");
		s1.setMarks(90);

		StudentService service = context.getBean(StudentService.class);
		service.addStudent(s1);
		List<Student> l = service.getAllStudents();
		for(Student s:l) System.out.println(s);
	}

}
