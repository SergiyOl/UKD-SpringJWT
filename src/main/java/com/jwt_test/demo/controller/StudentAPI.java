package com.jwt_test.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt_test.demo.exception.StudentNotFoundException;
import com.jwt_test.demo.entity.dto.RequestStudentDTO;
import com.jwt_test.demo.entity.dto.ResponceStudentDTO;
import com.jwt_test.demo.model.Student;
import com.jwt_test.demo.service.students.StudentServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class StudentAPI {
    private StudentServiceImpl studentService = null;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentAPI(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/api/students")
    public List<Student> listStudents() {
        logger.info("Received GET request to /students");
        return studentService.getAllStudents();
    }

    @GetMapping("/api/students/")
    public Optional<Student> getStudentById(@RequestParam(required = true) Long id) {
        logger.info("Received GET request to /students/?id=" + id);
        Optional<Student> responce = studentService.getStudentById(id);
        if (Optional.empty().equals(responce)) {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
        return responce;
    }

    @PostMapping("/api/students")
    public ResponceStudentDTO addStudent(@RequestBody RequestStudentDTO requestDTO, HttpServletResponse response) {
        logger.info("Received POST request to /students");
        Optional<Student> responce = studentService.addStudent(requestDTO.getId(), requestDTO.getName(),
                requestDTO.getAge());
        if (Optional.empty().equals(responce)) {
            throw new StudentNotFoundException("Student with ID " + requestDTO.getId() + " alreary exists");
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return (new ResponceStudentDTO(requestDTO.getId(), requestDTO.getName(), requestDTO.getAge()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/api/students")
    public ResponceStudentDTO updateStudent(@RequestBody RequestStudentDTO requestDTO) {
        logger.info("Received PUT request to /students");
        Optional<Student> responce = studentService.updateStudent(requestDTO.getId(), requestDTO.getName(),
                requestDTO.getAge());
        if (Optional.empty().equals(responce)) {
            throw new StudentNotFoundException("Student with ID " + requestDTO.getId() + " not found");
        }
        return (new ResponceStudentDTO(requestDTO.getId(), requestDTO.getName(), requestDTO.getAge()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/students/")
    public Optional<Student> deleteStudentById(@RequestParam(required = true) Long id, HttpServletResponse response) {
        logger.info("Received DELETE request to /students/?id=" + id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return studentService.deleteStudentById(id);
    }
}