package io.gainable.backend.service;

import io.gainable.backend.model.Result;
import io.gainable.backend.model.Student;
import io.gainable.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudentResult(long studentId, Result result) {
        var maybeStudent = studentRepository.findById(studentId);
        return maybeStudent
                .map(student -> {
                    result.setStudent(student);
                    student.getResults().add(result);
                    return student;
                })
                .map(studentRepository::save)
                .orElseThrow();
    }
}
