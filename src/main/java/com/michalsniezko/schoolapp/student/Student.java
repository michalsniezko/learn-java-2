package com.michalsniezko.schoolapp.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.michalsniezko.schoolapp.school.School;
import com.michalsniezko.schoolapp.studentprofile.StudentProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "T_STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "c_fname", length = 20)
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private int age;
    @Column(updatable = false)
    private String createdAt;
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private StudentProfile studentProfile;
    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference
    private School school;
}
