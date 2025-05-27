package com.michalsniezko.schoolapp.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StudentMapperTest {

    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDTOToStudent() {
        StudentDTO dto = new StudentDTO("John", "Doe", "john@mail.com", 1);

        Student student = studentMapper.toStudent(dto);

        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDTO() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john@mail.com");

        StudentResponseDTO dto = studentMapper.toStudentResponseDTO(student);
        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenStudentDTOIsNull() {
        var ex = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("StudentDTO cannot be NULL", ex.getMessage());
    }
}