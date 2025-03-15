package com.biocorp.intento1.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;


    @PostMapping("/schools")
    public ResponseEntity<SchoolDto> saveSchool(@RequestBody SchoolDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(schoolService.save(dto));
    }

    @GetMapping("/schools")
    public ResponseEntity<List<SchoolDto>> findAll(){
        return ResponseEntity.ok(schoolService.findAll());
    }
}
