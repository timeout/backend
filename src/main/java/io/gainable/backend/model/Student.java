package io.gainable.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
@Entity
public class Student implements Comparable<Student> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Result> results = new ArrayList<>();

    @ManyToMany
    private Set<SchoolClass> schoolClasses = new TreeSet<>();

    @Override
    public int compareTo(Student o) {
        var lastNameComp = lastName.compareTo(o.lastName);
        return (lastNameComp == 0)
                ? firstName.compareTo(o.firstName)
                : lastNameComp;
    }
}
