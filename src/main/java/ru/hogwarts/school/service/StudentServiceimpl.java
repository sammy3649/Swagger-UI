package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceimpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceimpl.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceimpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getFStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return null;
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Integer getStudentsCount() {
        return studentRepository.getStudentsCount();
    }

    @Override
    public Integer getStudentsAverageAge() {
        return studentRepository.getStudentsAverageAge();
    }

    @Override
    public Collection<Student> getLastStudent() {
        return studentRepository.getLastStudent();
    }

    @Override
    public List<String> getStudentNameStartWithA() {
        log.info("Was invoked method to get student starting with A");
        return studentRepository.findAll().stream().parallel()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(a -> a.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public int getStudentsAverageAgeWithStream() {
        log.info("Was invoked method for get students average age with stream ");
        return (int) studentRepository.findAll().stream().parallel()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }
}