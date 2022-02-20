package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentServiceimpl implements StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
    private long lastId = 0;
    @Override

    public Student createStudent(Student student) {
        student.setId(++lastId);
        studentMap.put(lastId, student);
        return student;
    }

    @Override
    public Student getFStudent(Long id) {
        return studentMap.get(id);
    }

    @Override
    public Student deleteStudent(Long id) {
        return studentMap.remove(id);
    }

    @Override
    public Student updateStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            studentMap.put(student.getId(), student);
            return student;
        }
        return null;
    }

    @Override
    public Collection<Student> getByAge(int age) {
        return studentMap.values().stream().filter(student -> Objects.equals(student.getAge(), age)).collect(Collectors.toList());
    }
}
