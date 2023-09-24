package io.gainable.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.TreeSet;

@Data
@Entity
public class SchoolClass implements Comparable<SchoolClass> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int year;
    private String subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Teacher teacher;

    @ManyToMany
    private Set<Student> students = new TreeSet<>();

    @Override
    public int compareTo(SchoolClass o) {
        var compYear = year - o.year;
        return (compYear == 0)
                ? subject.compareTo(o.subject)
                : compYear;
    }
}
