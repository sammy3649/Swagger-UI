package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentServiceimpl implements StudentService {
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
    public Collection<Student> getByAge(int age) {
        return studentRepository.findByAge(age);    }
}
