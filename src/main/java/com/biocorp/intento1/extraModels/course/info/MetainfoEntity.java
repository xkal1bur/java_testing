package com.biocorp.intento1.extraModels.course.info;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


//@SuperBuilder
@Embeddable
public class MetainfoEntity{
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private String createdBy;
    private String lastModifiedBy;

}
