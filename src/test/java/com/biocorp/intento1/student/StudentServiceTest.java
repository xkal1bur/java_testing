package com.biocorp.intento1.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentMapper studentMapper;
    @Mock
    private StudentRepository studentRepository;

    private AutoCloseable closeable;


    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close(); // Cierra los recursos asociados a los mocks
    }

    @Test
    public void shouldSuccessfullySaveStudent() {
        // given
        StudentDto dto = new StudentDto("arturo", "barrantes", "asd@gmail.com", 1);
        Student student = new Student("arturo", "barrantes", "asd@gmail.com", 20);
        Student savedStudent = new Student("arturo", "barrantes", "asd@gmail.com", 20);
        savedStudent.setId(1);

        // Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto("arturo", "barrantes", "asd@gmail.com"));

        // when
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        // then
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());

        Mockito.verify(studentMapper, Mockito.times(1))
                .toStudent(dto);
        Mockito.verify(studentMapper, Mockito.times(1))
                .toStudentResponseDto(savedStudent);
        Mockito.verify(studentRepository, Mockito.times(1))
                .save(student);


    }

    @Test
    public void shouldSuccessfullyRetrieveAllStudents() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("arturo", "barrantes", "asd@gmail.com", 19));

        // Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(
                new StudentResponseDto("arturo", "barrantes", "asd@gmail.com")
        );

        // When
        List<StudentResponseDto> responseDtos = studentService.findAll();
        // Then

        assertEquals(students.size(), responseDtos.size());
        verify(studentRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void shouldReturnStudentById() {
        // given
        //List<Student> studentList = new ArrayList<>();
        Student s = new Student("arturo", "barrantes", "asd@gmail.com", 19);
        //studentList.add(s);
        StudentResponseDto sr = new StudentResponseDto("arturo", "barrantes", "asd@gmail.com");
        Optional<Student> student = Optional.of(s);
        //StudentResponseDto student = new StudentResponseDto("Arturo", "B", "asd@gmail.com");
        Integer id = 1;

        // mock
        when(studentRepository.findById(any(Integer.class))).thenReturn(student);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(sr);

        // when
        Optional<StudentResponseDto> studentResponseDto = studentService.findStudentById(id);

        // then
        assertNotNull(studentResponseDto);
        assertTrue(studentResponseDto.isPresent());
        assertEquals(studentResponseDto.get().email(), s.getEmail());
        assertEquals(studentResponseDto.get().firstname(), s.getFirstname());
        assertEquals(studentResponseDto.get().lastname(), s.getLastname());

        verify(studentRepository, times(id)).findById(id);
    }

    @Test
    public void shouldReturnStudentsFirstnameContaining(){
        // given
        List<Student> studentList = new ArrayList<>();
        Student s = new Student("arturo", "barrantes", "asd@gmail.com", 19);
        studentList.add(s);
        String query = "ar";

        // mock
        when(studentRepository.findAllByFirstnameContaining(query)).thenReturn(studentList);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(new StudentResponseDto(
                "arturo", "barrantes", "asd@gmail.com"
        ));

        // when

        List<StudentResponseDto> responseDtos = studentService.findAllByFirstnameContaining(query);

        // then

        assertEquals(responseDtos.size(), studentList.size());
        assertEquals(responseDtos.get(0).firstname(), studentList.get(0).getFirstname());
        assertEquals(responseDtos.get(0).lastname(), studentList.get(0).getLastname());
        assertEquals(responseDtos.get(0).email(), studentList.get(0).getEmail());

        verify(studentRepository, times(1)).findAllByFirstnameContaining(query);
    }

    @Test
    public void shouldDeleteStudent(){
        // given
        Integer studentId = 1;

        // mock
        when(studentRepository.existsById(studentId)).thenReturn(true);

        // when
        boolean del = studentService.deleted(studentId);

        // then
        assertTrue(del);
        verify(studentRepository,times(1)).deleteById(any(Integer.class));

    }
}