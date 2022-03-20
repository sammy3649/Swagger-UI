package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    Student getFStudent(Long id);

    Student deleteStudent(Long id);

    Student updateStudent(Student student);

    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int minAge, int maxAge);

    Integer getStudentsCount();

    Integer getStudentsAverageAge();

    Collection<Student> getLastStudent();
}
