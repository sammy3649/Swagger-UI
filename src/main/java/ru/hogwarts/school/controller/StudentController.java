package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public Student createStudent(@org.springframework.web.bind.annotation.RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.getFStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);

    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student putStudent = studentService.updateStudent(student);
        if (putStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(putStudent);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        Student deleteStudent = studentService.deleteStudent(id);
        if (deleteStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(deleteStudent);
    }

    @GetMapping(params = {"age"})
    public List<Student> findByAge(@RequestParam(required = false) int age) {
        return studentService.findByAge(age);
    }

    @GetMapping(params = {"minAge", "maxAge"})
    public List<Student> findByAgeBetween(
            @RequestParam(required = false) int minAge,
            @RequestParam(required = false) int maxAge
    ) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/count")
    public Integer getStudentsCount() {
        return studentService.getStudentsCount();
    }

    @GetMapping("/average")
    public Integer getStudentsAverageAge() {
        return studentService.getStudentsAverageAge();
    }

    @GetMapping("/laststudents")
    public ResponseEntity<Collection<Student>> getLastStudent() {
        Collection<Student> lastStudent = studentService.getLastStudent();
        if (lastStudent.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(lastStudent);
    }

    @GetMapping("/startwitha")
    public List<String> getStudentNameStartWithA() {
        return studentService.getStudentNameStartWithA();
    }

    @GetMapping("/averagestream")
    public int getStudentsAverageAgeWithStream() {
        return studentService.getStudentsAverageAgeWithStream();
    }
}

