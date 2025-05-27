package com.michalsniezko.schoolapp.school;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.michalsniezko.schoolapp.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "school")
    @JsonManagedReference
    private List<Student> students;
}
