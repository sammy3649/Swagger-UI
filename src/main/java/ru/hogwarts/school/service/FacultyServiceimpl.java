package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        log.info("Was invoked method for create faculty {}", faculty);
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        log.info("Was invoked method to get faculty {}", id);
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty deleteFaculty(Long id) {
        log.info("Was invoked method for delete faculty {}", id);
        facultyRepository.deleteById(id);
        return null;
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        log.info("Was invoked method for update faculty {}", faculty);
        return facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> findByColor(String color) {
        log.info("Was invoked method to find by color {}", color);
        return facultyRepository.findByColor(color);
    }

    @Override
    public List<Faculty> findByColorOrName(String color, String name) {
        log.info("Was invoked method to find by color {} or name {}", color, name);
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
