package io.gainable.backend.service;

import io.gainable.backend.model.SchoolClass;
import io.gainable.backend.model.Student;
import io.gainable.backend.model.Teacher;
import io.gainable.backend.repository.SchoolClassRepository;
import io.gainable.backend.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;
    private final TeacherRepository teacherRepository;

    public SchoolClassService(
            SchoolClassRepository schoolClassRepository,
            TeacherRepository teacherRepository
            ) {
        this.schoolClassRepository = schoolClassRepository;
        this.teacherRepository = teacherRepository;
    }

    public SchoolClass createSchoolClass(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }


    public SchoolClass findSchoolClass(long id) {
        return schoolClassRepository.findById(id)
                .orElseThrow();
    }

    public SchoolClass addTeacher(long id, Teacher teacher) {
       var maybeSchoolClass = schoolClassRepository.findById(id);
        return  maybeSchoolClass
                .map(schoolClass -> {
                    var maybeTeacher = teacherRepository.findById(teacher.getId());
                    return maybeTeacher.map(t -> {
                                schoolClass.setTeacher(t);
                                return schoolClass;
                            })
                            .orElseThrow();
                })
                .map(schoolClassRepository::save)
                .orElseThrow();
    }

    public SchoolClass addStudent(long id, Student student) {
        var maybeSchoolClass = schoolClassRepository.findById(id);
        return maybeSchoolClass
                .map(schoolClass -> {
                    student.getSchoolClasses().add(schoolClass);
                    schoolClass.getStudents().add(student);
                    return schoolClass;
                })
                .map(schoolClassRepository::save)
                .orElseThrow();
    }

    public Set<Student> getAllStudents(long id) {
        var maybeSchoolClass = schoolClassRepository.findById(id);
        return maybeSchoolClass
                .map(SchoolClass::getStudents)
                .orElseThrow();
    }
}
