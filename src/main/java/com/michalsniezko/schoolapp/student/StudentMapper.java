package com.michalsniezko.schoolapp.student;

import com.michalsniezko.schoolapp.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student toStudent(StudentDTO dto) {
        if(dto == null) {
            throw new NullPointerException("StudentDTO cannot be NULL");
        }

        var student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        var school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);
        return student;
    }

    public StudentResponseDTO toStudentResponseDTO(Student student) {
        return new StudentResponseDTO(student.getFirstName(), student.getLastName(), student.getEmail());
    }
}
