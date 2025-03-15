package com.biocorp.intento1.extraModels.course.inheritance;

import com.biocorp.intento1.extraModels.course.resource.Resource;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class File extends Resource {
    private String type;
}
