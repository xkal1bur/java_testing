package com.biocorp.intento1.studentprofile;

import com.biocorp.intento1.student.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "T_STUDENTPROFILE")
public class StudentProfile {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String bio;

    @OneToOne
    @JoinColumn(
            name = "student_id"
    )
    private Student student;

    public StudentProfile() {
    }

    public StudentProfile(String bio) {
        this.bio = bio;
    }
}
