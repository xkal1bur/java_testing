package com.biocorp.intento1.student;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();

    }

    @Test
    public void shouldMatchStudentDtoToStudent() {
        StudentDto dto = new StudentDto("Arturo", "Ba", "a@gmail.com", 2);
        Student student = mapper.toStudent(dto);
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        Student student = new Student("arturo", "barrantes", "asd@gmail.com", 2);
        StudentResponseDto response = mapper.toStudentResponseDto(student);

        assertEquals(student.getFirstname(), response.firstname());
        assertEquals(student.getLastname(), response.lastname());
        assertEquals(student.getEmail(), response.email());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenStudentDtoIsNull() {
        NullPointerException exp = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("Student Dto is null", exp.getMessage());
    }
}