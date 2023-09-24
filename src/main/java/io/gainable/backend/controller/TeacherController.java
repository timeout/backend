package io.gainable.backend.controller;

import io.gainable.backend.model.SchoolClass;
import io.gainable.backend.model.Teacher;
import io.gainable.backend.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/teachers/{id}")
    public Teacher getTeacher(@PathVariable long id) {
        return teacherService.findTeacher(id);
    }

    @GetMapping List<SchoolClass> getSchoolClasses(@PathVariable long id) {
        // TODO
        return null;
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

}
