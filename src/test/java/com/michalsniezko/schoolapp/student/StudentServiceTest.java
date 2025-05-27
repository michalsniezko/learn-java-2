package com.michalsniezko.schoolapp.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    // which service we want to test?
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSuccessfullySaveAStudent() {
        //given
        StudentDTO dto = new StudentDTO("John", "Doe", "john@mail.com", 1);
        Student student = getStudent();
        Student savedStudent = getStudent();
        savedStudent.setId(1);

        //mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDTO(savedStudent)).thenReturn(new StudentResponseDTO(
                "John", "Doe", "john@mail.com")
        );

        //when
        StudentResponseDTO responseDTO = studentService.createStudent(dto);

        //then
        assertEquals(dto.firstName(), responseDTO.firstName());
        assertEquals(dto.lastName(), responseDTO.lastName());
        assertEquals(dto.email(), responseDTO.email());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDTO(savedStudent);
    }

    @Test
    public void shouldReturnAllStudents() {
        //given
        Student student = getStudent();

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        //mock the calls
        when(studentRepository.findAll()).thenReturn(studentList);
        StudentResponseDTO responseDTO = new StudentResponseDTO(
                "John", "Doe", "john@mail.com");
        when(studentMapper.toStudentResponseDTO(any(Student.class))).thenReturn(responseDTO);

        //when
        List<StudentResponseDTO> responseDTOS = studentService.findAll();

        //then
        assertEquals(studentList.size(), responseDTOS.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindStudentById() {
        //given
        int id = 1;
        Student student = getStudent();
        StudentResponseDTO expectedDTO = new StudentResponseDTO(
                "John", "Doe", "john@mail.com");

        //mock the calls
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDTO(student)).thenReturn(expectedDTO);

        //when
        StudentResponseDTO actualDTO = studentService.findById(id);

        assertEquals(actualDTO.email(), expectedDTO.email());
        assertEquals(actualDTO.firstName(), expectedDTO.firstName());
        assertEquals(actualDTO.lastName(), expectedDTO.lastName());
    }

    public void shouldFindStudentByName() {
        //given
        Student student = getStudent();

        List<Student> students = new ArrayList<>();
        students.add(student);

        String nameFragment = "John";

        //mock the calls
        when(studentRepository.findAllByFirstNameContaining(nameFragment)).thenReturn(students);
        StudentResponseDTO responseDTO = new StudentResponseDTO(
                "John", "Doe", "john@mail.com");
        when(studentMapper.toStudentResponseDTO(any(Student.class))).thenReturn(responseDTO);

        var responseDTOList = studentService.findAllByFirstNameContaining(nameFragment);

        assertEquals(students.size(), responseDTOList.size());
        verify(studentRepository, times(1)).findAllByFirstNameContaining(nameFragment);
    }

    private static Student getStudent() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john@mail.com");
        return student;
    }
}