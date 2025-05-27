package com.michalsniezko.schoolapp.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public School toSchool(SchoolDTO schoolDTO) {
        School school = new School();
        school.setName(schoolDTO.name());
        return school;
    }

    public SchoolDTO toSchoolDTO(School school) {
        return new SchoolDTO(school.getName());
    }
}

