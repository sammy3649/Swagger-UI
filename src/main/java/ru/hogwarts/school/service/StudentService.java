package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student createStudent(Student student);

    Student getFStudent(Long id);

    Student deleteStudent(Long id);

    Student updateStudent(Student student);

    Collection<Student> getByAge(int age);
}
