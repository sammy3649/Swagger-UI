package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@org.springframework.web.bind.annotation.RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);

    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty putFaculty = facultyService.updateFaculty(faculty);
        if (putFaculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(putFaculty);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
            facultyService.deleteFaculty(id);
            return ResponseEntity.ok().build();}

    @GetMapping(params = {"color"})
    public List<Faculty> findByColor(
            @RequestParam(required = false) String color) {
        return facultyService.findByColor(color);
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<List<Faculty>> findByColorOrName(
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String name) {
        List<Faculty> facultyByColorOrName = facultyService.findByColorOrName(color, name);
        if (facultyByColorOrName.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(facultyByColorOrName);
    }    }
















