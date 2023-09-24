package io.gainable.backend.controller;

import io.gainable.backend.model.SchoolClass;
import io.gainable.backend.model.Student;
import io.gainable.backend.model.Teacher;
import io.gainable.backend.service.SchoolClassService;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @PostMapping("/class")
    public SchoolClass createSchoolClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.createSchoolClass(schoolClass);
    }

    @GetMapping("/class/{id}")
    public SchoolClass getSchoolClass(@PathVariable long id) {
        return schoolClassService.findSchoolClass(id);
    }

    @PutMapping("/class/{id}/teacher")
    public void addTeacher(@PathVariable long id, @RequestBody Teacher teacher) {
        schoolClassService.addTeacher(id, teacher);
    }

    @PutMapping("/class/{id}/student")
    public void addStudent(@PathVariable long id, @RequestBody Student student) {
        schoolClassService.addStudent(id, student);
    }
}
