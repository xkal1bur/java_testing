package com.biocorp.intento1.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto dto){
        var student = studentMapper.toStudent(dto);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAll(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<StudentResponseDto> findStudentById(Integer studentId){
        return studentRepository.findById(studentId)
                .map(studentMapper::toStudentResponseDto);
    }

    public List<StudentResponseDto> findAllByFirstnameContaining(String studentName) {
        return studentRepository.findAllByFirstnameContaining(studentName)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public boolean deleted(Integer studentId){
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return true;
        }
        return false;
    }
}
