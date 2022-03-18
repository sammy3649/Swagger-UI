package ru.hogwarts.school.controller;


import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.FacultyServiceimpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.hogwarts.school.constants.TestConstants.*;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerWithMockTest {

    private Faculty faculty;

    private Collection<Faculty> facultyList;

    private JSONObject facultyObj;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyServiceimpl facultyServiceimpl;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @BeforeEach
    void initTest() throws JSONException {
        faculty = new Faculty();
        faculty.setId(FACULTY_ID);
        faculty.setName(NAME);
        faculty.setColor(COLOR);

        Faculty faculty1 = new Faculty();
        faculty1.setId(FACULTY_ID);
        faculty1.setName(NAME);
        faculty1.setColor(COLOR);

        Faculty faculty2 = new Faculty();
        faculty2.setId(FACULTY_ID);
        faculty2.setName(NAME);
        faculty2.setColor(COLOR);

        facultyList = List.of(
                faculty,
                faculty1,
                faculty2
        );


        facultyObj = new JSONObject();
        facultyObj.put("name", NAME);
        facultyObj.put("color", COLOR);


    }

    @Test
    void createFaculty() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObj.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(FACULTY_ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));


    }

    @Test
    void getFacultyBy() throws Exception {
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + FACULTY_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(FACULTY_ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));

    }

    @Test
    void updateFaculty() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObj.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(FACULTY_ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    void deleteFacultyTest() throws Exception {
        when(facultyRepository.getById(FACULTY_ID)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/{id}",FACULTY_ID))
                .andExpect(status().isOk());
    }

    @Test
    void findFacultyByColor() throws Exception {
        when(facultyRepository.findByColor(COLOR)).thenReturn((List<Faculty>) facultyList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/?color=" + COLOR)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void findByColorOrName() throws Exception {
        when(facultyRepository.findByColorOrNameIgnoreCase(anyString(), anyString())).thenReturn((List<Faculty>) facultyList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/?name=" + COLOR + "&name=" + NAME)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].name").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].color").isNotEmpty());
    }

}