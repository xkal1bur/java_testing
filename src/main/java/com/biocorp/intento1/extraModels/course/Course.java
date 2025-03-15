package com.biocorp.intento1.extraModels.course;

import com.biocorp.intento1.extraModels.course.info.MetainfoEntity;
import com.biocorp.intento1.extraModels.course.section.Section;
import com.biocorp.intento1.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="t_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course{
    @Id
    @GeneratedValue
    private Long id;

    @Embedded MetainfoEntity metainfoEntity;


    @Column(name="name")
    private String nombre;


    @ManyToMany
    @JoinTable(
            name = "coursesStudent",
            joinColumns = {
                    @JoinColumn(name = "course_id")
            },
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private Set<Student> studentSet = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private List<Section> sections;
}
