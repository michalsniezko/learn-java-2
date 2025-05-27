package com.michalsniezko.schoolapp.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public SchoolDTO create(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.save(schoolDTO);
    }

    @GetMapping
    public List<SchoolDTO> all() {
        return schoolService.getAll();
    }
}
