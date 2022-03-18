package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FacultyServiceimpl implements FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private long lastId = 0;

    @Override
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        facultyMap.put(lastId, faculty);
        return faculty;
    }

    @Override
    public Faculty getFaculty(Long id) {
        return facultyMap.get(id);
    }

    @Override
    public Faculty deleteFaculty(Long id) {
        return facultyMap.remove(id);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        if (facultyMap.containsKey(faculty.getId())) {
            facultyMap.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    @Override
    public Collection<Faculty> getByColor(String color) {
        return facultyMap.values().stream().filter(student -> Objects.equals(student.getColor(), color)).collect(Collectors.toList());
    }

}
