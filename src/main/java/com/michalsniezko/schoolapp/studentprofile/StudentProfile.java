package com.michalsniezko.schoolapp.studentprofile;

import com.michalsniezko.schoolapp.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String bio;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
