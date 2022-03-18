package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;

import static ru.hogwarts.school.constant.TestConstants.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTests {

    private Student student;

    private String baseUrl;

    private long studentId;

    @LocalServerPort
    private int localPort;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void initTestData() {
        student = new Student();
        student.setId(115L);
        student.setName("Name");
        student.setAge(20);
    }

    @BeforeEach
    public void setBaseUrl() {
        this.baseUrl = "http://localhost:" + localPort + "/student/";
    }

    @AfterEach
    void delete() {
        testRestTemplate.delete(baseUrl + studentId);
    }


    @Test
    void contextLoads() {
        org.assertj.core.api.Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void createStudentTest() {
        org.assertj.core.api.Assertions.assertThat(this.testRestTemplate.postForObject(baseUrl, student, String.class)).isNotNull();
        org.assertj.core.api.Assertions.assertThat(this.testRestTemplate.postForObject(baseUrl, student, Student.class)).isEqualTo(student);
    }

    @Test
    void deleteStudent() {
        studentId = testRestTemplate.postForObject(baseUrl, student, Student.class).getId();
        student.setId(studentId);
        testRestTemplate.delete(baseUrl + studentId);

        org.assertj.core.api.Assertions.assertThat(this.testRestTemplate.getForObject(baseUrl + studentId, Student.class)).isNotIn(student);
    }

    @Test
    void getStudent() {
        studentId = testRestTemplate.postForObject(baseUrl, student, Student.class).getId();

        org.assertj.core.api.Assertions.assertThat(this.testRestTemplate.getForObject(baseUrl + studentId, Student.class)).isEqualTo(student);
    }

    @Test
    void upDateStudent() {
        student.setAge(BASE_AGE);

        studentId = testRestTemplate.postForObject(baseUrl, student, Student.class).getId();

        student.setId(studentId);

        testRestTemplate.put(baseUrl, student);

        org.assertj.core.api.Assertions.assertThat(this.testRestTemplate.getForObject(baseUrl + studentId, Student.class)).isEqualTo(student);
    }

    @Test
    void findByAge() {
        student.setAge(AGE_STUDENT1);

        studentId = testRestTemplate.postForObject(baseUrl, student, Student.class).getId();

        student.setId(studentId);

        org.assertj.core.api.Assertions.assertThat(this.testRestTemplate.getForObject(baseUrl + "?age=" + AGE_STUDENT1, Collection.class)).isNotNull();
    }

    @Test
    void findByAgeBetween() {
        student.setAge(AGE_STUDENT2);
        studentId = testRestTemplate.postForObject(baseUrl, student, Student.class).getId();
        student.setAge(AGE_STUDENT3);
        studentId = testRestTemplate.postForObject(baseUrl, student, Student.class).getId();
        student.setAge(AGE_STUDENT4);
        studentId = testRestTemplate.postForObject(baseUrl, student, Student.class).getId();

        Assertions.assertThat(this.testRestTemplate.getForObject(baseUrl + "?min=" + AGE_STUDENT5 + "&max=" + AGE_STUDENT6, ArrayList.class).size()).isGreaterThan(80).isLessThan(100);
    }

}