package com.michalsniezko.schoolapp.student;

import com.michalsniezko.schoolapp.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(StudentDTO studentDTO) {
        var student = new Student();
        student.setFirstName(studentDTO.firstName());
        student.setLastName(studentDTO.lastName());
        student.setEmail(studentDTO.email());
        var school = new School();
        school.setId(studentDTO.schoolId());
        student.setSchool(school);
        return student;
    }

    public StudentResponseDTO toStudentResponseDTO(Student student) {
        return new StudentResponseDTO(student.getFirstName(), student.getLastName(), student.getEmail());
    }
}
