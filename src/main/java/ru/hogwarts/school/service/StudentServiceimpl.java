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
        log.info("Was invoked method for create student {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student getFStudent(Long id) {
        log.info("Was invoked method for get student {}", id);
        return studentRepository.findById(id).get();
    }

    @Override
    public Student deleteStudent(Long id) {
        log.info("Was invoked method for delete student {}", id);
        studentRepository.deleteById(id);
        return null;
    }

    @Override
    public Student updateStudent(Student student) {
        log.info("Was invoked method for update student {}", student);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findByAge(int age) {
        log.info("Was invoked method to find by age {}", age);
        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> findByAgeBetween(int minAge, int maxAge) {
        log.info("Was invoked method to find by age min {}, max {}", maxAge, minAge);
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Integer getStudentsCount() {
        log.info("Was invoked method for get students count");
        return studentRepository.getStudentsCount();
    }

    @Override
    public Integer getStudentsAverageAge() {
        log.info("Was invoked method for get students average age");
        return studentRepository.getStudentsAverageAge();
    }

    @Override
    public Collection<Student> getLastStudent() {
        log.info("Was invoked method for get last students");
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

    @Override
    public void getStudentsWithStreams() {
        Thread streamStudents = new Thread(() -> {
            printName(3L);
            printName(4L);
        });
        streamStudents.setName("names streams 1");
        Thread streamStudents2 = new Thread(() -> {
            printName(5L);
            printName(6L);
        });
        streamStudents.setName("names streams 2");
        streamStudents.start();
        streamStudents2.start();
        printName(1L);
        printName(2L);
    }

    @Override
    public void getStudentsWithStreamsSynchronized() {
        Thread streamStudents = new Thread(() -> {
            printNameSynchronized(3L);
            printNameSynchronized(4L);
        });
        streamStudents.setName("names streams 1");
        Thread streamStudents2 = new Thread(() -> {
            printNameSynchronized(5L);
            printNameSynchronized(6L);
        });
        streamStudents.setName("names streams 2");
        printNameSynchronized(1L);
        printNameSynchronized(2L);
        streamStudents.start();
    }

    private void printName(Long id) {
        String names = studentRepository.getById(id).getName();
        System.out.println(names);
    }

    private synchronized void printNameSynchronized(Long id) {
        String names = studentRepository.getById(id).getName();
        System.out.println(names);
    }

}
