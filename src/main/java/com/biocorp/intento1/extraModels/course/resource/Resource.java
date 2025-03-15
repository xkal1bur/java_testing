package com.biocorp.intento1.extraModels.course.resource;

import com.biocorp.intento1.extraModels.course.info.MetainfoEntity;
import com.biocorp.intento1.extraModels.course.lecture.Lecture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)

public class Resource {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded MetainfoEntity metainfo;

    @Column
    private String name;

    @Column
    private String url;

    @OneToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}
