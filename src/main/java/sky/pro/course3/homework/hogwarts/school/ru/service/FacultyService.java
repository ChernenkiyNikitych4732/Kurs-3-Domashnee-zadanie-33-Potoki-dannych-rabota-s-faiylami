package sky.pro.course3.homework.hogwarts.school.ru.service;

import org.springframework.stereotype.Service;
import sky.pro.course3.homework.hogwarts.school.ru.model.Faculty;
import sky.pro.course3.homework.hogwarts.school.ru.model.Student;
import sky.pro.course3.homework.hogwarts.school.ru.repositories.FacultyRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentService studentService;

    public FacultyService(FacultyRepository facultyRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty deleteFaculty(long id) {
        Faculty facultyForDelete = facultyRepository.findById(id).get();
        facultyRepository.deleteById(id);
        return facultyForDelete;
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public Set<Faculty> getFacultyByColorOrName(String param) {
        Set<Faculty> result = new HashSet<>();
        result.addAll(facultyRepository.findByColorIgnoreCase(param));
        result.addAll(facultyRepository.findByNameIgnoreCase(param));
        return result;
    }

    public Collection<Student> getStudentsByFacultyId(Long id) {
        return studentService.getByFacultyId(id);
    }
}
