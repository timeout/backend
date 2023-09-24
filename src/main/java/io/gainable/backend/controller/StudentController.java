package io.gainable.backend.controller;

import io.gainable.backend.model.Result;
import io.gainable.backend.model.Student;
import io.gainable.backend.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addResult(@PathVariable long id, @RequestBody Result result) {
        studentService.addStudentResult(id, result);
    }
}
