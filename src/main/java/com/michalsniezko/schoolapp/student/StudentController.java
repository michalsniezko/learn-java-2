package com.michalsniezko.schoolapp.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponseDTO post(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @GetMapping
    public List<StudentResponseDTO> findAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public StudentResponseDTO findStudentById(@PathVariable Integer studentId) {
        return studentService.findById(studentId);
    }

    @GetMapping("/search/{studentFirstName}")
    public List<StudentResponseDTO> findStudentsByFirstName(@PathVariable String studentFirstName) {
        return studentService.findAllByFirstNameContaining(studentFirstName);
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("studentId") Integer id) {
        studentService.deleteById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    @PostMapping("/post-order")
//    public String postOrder(@RequestBody Order order) {
//        return "Request accepted and order is " + order.toString();
//    }
//
//    @PostMapping("/post-order-record")
//    public String postOrderRecord(@RequestBody OrderRecord order) {
//        return "Request accepted and order is " + order.toString();
//    }
//
//    // http://localhost:8080/hello/alibou
//    @GetMapping("/hello/{user-name}")
//    public String pathVariable(@PathVariable("user-name") String userName) {
//        return "my value = " + userName;
//    }
//
//    // http://localhost:8080/hello/alibou?param=value&param2=value2
//    @GetMapping("/hello")
//    public String paramVar(
//            @RequestParam("user-name") String userName,
//            @RequestParam("user-lastname") String userLastName
//    ) {
//        return "my value = " + userName + " " + userLastName;
//    }

}
