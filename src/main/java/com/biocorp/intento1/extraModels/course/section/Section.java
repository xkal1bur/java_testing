package com.biocorp.intento1.extraModels.course.section;

import com.biocorp.intento1.extraModels.course.Course;
import com.biocorp.intento1.extraModels.course.lecture.Lecture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String name;

    private int sectionOrder;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "section")
    private List<Lecture> lectures;

}
