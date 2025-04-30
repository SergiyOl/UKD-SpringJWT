package com.jwt_test.demo.service.students;

import java.util.List;
import java.util.Optional;

import com.jwt_test.demo.model.Student;

public interface StudentService {
    public List<Student> getAllStudents();

    public Optional<Student> getStudentById(Long id);

    public Optional<Student> addStudent(Long id, String name, int age);

    public Optional<Student> updateStudent(Long id, String name, int age);

    public Optional<Student> deleteStudentById(Long id);
}
