package com.michalsniezko.schoolapp.student;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDTO createStudent(StudentDTO studentDTO) {
        Student student = this.studentMapper.toStudent(studentDTO);
        Student saved = studentRepository.save(student);

        return this.studentMapper.toStudentResponseDTO(saved);
    }

    public List<StudentResponseDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO findById(Integer studentId) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return studentMapper.toStudentResponseDTO(student);
    }

    public List<StudentResponseDTO> findAllByFirstNameContaining(String studentFirstName) {
        return studentRepository.findAllByFirstNameContaining(studentFirstName)
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }
}
