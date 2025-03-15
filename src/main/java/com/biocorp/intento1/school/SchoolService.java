package com.biocorp.intento1.school;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public School toSchool(SchoolDto dto) {
        return new School(dto.name());
    }

    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }

    public SchoolDto save(SchoolDto dto){
        return toSchoolDto(schoolRepository.save(toSchool(dto)));
    }

    public List<SchoolDto> findAll(){
        return schoolRepository.findAll()
                .stream()
                .map(this::toSchoolDto)
                .collect(Collectors.toList());
    }
}
