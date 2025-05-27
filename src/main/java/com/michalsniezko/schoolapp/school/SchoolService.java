package com.michalsniezko.schoolapp.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDTO save(SchoolDTO schoolDTO) {
        var schoolToSave = schoolMapper.toSchool(schoolDTO);
        School savedSchool = schoolRepository.save(schoolToSave);
        return schoolMapper.toSchoolDTO(savedSchool);
    }

    public List<SchoolDTO> getAll() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDTO)
                .collect(Collectors.toList());
    }
}
