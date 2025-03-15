package com.biocorp.intento1.extraModels.course.lecture;

import com.biocorp.intento1.extraModels.course.info.MetainfoEntity;
import com.biocorp.intento1.extraModels.course.resource.Resource;
import com.biocorp.intento1.extraModels.course.section.Section;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Lecture{
    @Id
    @GeneratedValue
    private Long id;

    @Embedded MetainfoEntity metainfoEntity;


    @Column
    private String nameOfLecture;

    @ManyToOne
    @JoinColumn(name = "sectionId")
    private Section section;

    @OneToOne
    @JoinColumn(name = "resourceId")
    private Resource resource;
}
