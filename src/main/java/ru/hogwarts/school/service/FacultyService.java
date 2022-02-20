package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    Faculty deleteFaculty(Long id);

    Faculty updateFaculty(Faculty faculty);

    Collection<Faculty> getByColor(String color);
}
