package io.gainable.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int rawMark;
    private int totalMark;
    private double grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Student student;
}
