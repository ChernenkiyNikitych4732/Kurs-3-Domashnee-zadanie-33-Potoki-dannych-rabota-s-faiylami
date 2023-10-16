package sky.pro.course3.homework.hogwarts.school.ru.controller;

import org.springframework.web.bind.annotation.*;
import sky.pro.course3.homework.hogwarts.school.ru.model.Faculty;
import sky.pro.course3.homework.hogwarts.school.ru.model.Student;
import sky.pro.course3.homework.hogwarts.school.ru.service.FacultyService;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping
    public Faculty getFaculty(@RequestParam Long id) {
        return facultyService.getFaculty(id);
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping
    public Faculty deleteFaculty(@RequestParam Long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping("/by-color")
    public Collection<Faculty> getFacultyByColor(@RequestParam String color) {
        return facultyService.getFacultyByColor(color);
    }

    @GetMapping("by-or-color")
    public Set<Faculty> getByColorOrNameIgnoreCase(@RequestParam String param) {
        return facultyService.getFacultyByColorOrName(param);
    }

    @GetMapping("/students-by-faculty-id")
    public Collection<Student> getStudentsByFacultyId(@RequestParam Long id) {
        return facultyService.getStudentsByFacultyId(id);
    }
}