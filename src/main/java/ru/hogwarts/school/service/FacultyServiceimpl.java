package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class FacultyServiceimpl implements FacultyService {
    private static final Logger log = LoggerFactory.getLogger(FacultyServiceimpl.class);
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceimpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
        return null;
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    @Override
    public List<Faculty> findByColorOrName(String color, String name) {
        return facultyRepository.findByColorOrNameIgnoreCase(color, name);
    }

    @Override
    public String getTheLongestName() {
        log.info("Was invoked method to get the longest name");
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse(null);
    }
}