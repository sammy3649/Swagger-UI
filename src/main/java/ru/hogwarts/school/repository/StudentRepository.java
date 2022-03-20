package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT count(*) FROM student", nativeQuery = true)
    Integer getStudentsCount();

    @Query(value = "SELECT AVG (age) FROM student", nativeQuery = true)
    Integer getStudentsAverageAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5 ", nativeQuery = true)
    List<Student> getLastStudent();
}