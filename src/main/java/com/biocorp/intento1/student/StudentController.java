package com.biocorp.intento1.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto dto) {
        return studentService.saveStudent(dto);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDto>> findAllAlumnos() {
        return ResponseEntity.ok(studentService.findAll());
    }



    @GetMapping("/students/{student_id}")
    public ResponseEntity<StudentResponseDto> findStudentById(@PathVariable("student_id") Integer studentId) {
        return studentService.findStudentById(studentId)
                .map(ResponseEntity::ok) // Si el estudiante existe, devuelve 200 OK con el cuerpo
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no, devuelve 404 Not Found
    }

    @GetMapping("/students/search/{student-name}")
    public ResponseEntity<List<StudentResponseDto>> findStudentByName(@PathVariable("student-name") String studentName) {
        List<StudentResponseDto> students = studentService.findAllByFirstnameContaining(studentName);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build(); // Si no hay resultados, devolver 404 Not Found
        }
        return ResponseEntity.ok(students); // Devolver 200 OK con la lista de estudiantes
    }

    @DeleteMapping("/students/{student-id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("student-id") Integer studentId) {
        if (studentService.deleted(studentId)) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ){
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMsg = error.getDefaultMessage();
                    errors.put(fieldName,errorMsg);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
