package sky.pro.course3.homework.hogwarts.school.ru.controller;

import org.springframework.web.bind.annotation.*;
import sky.pro.course3.homework.hogwarts.school.ru.model.Faculty;
import sky.pro.course3.homework.hogwarts.school.ru.model.Student;
import sky.pro.course3.homework.hogwarts.school.ru.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping
    public Student getStudent(@RequestParam Long id) {
        return studentService.findStudent(id);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping
    public Student deleteStudent(@RequestParam Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/by-age")
    public Collection<Student> getStudentByAge(@RequestParam int age) {
        return studentService.findByAge(age);
    }

    @GetMapping("by-age-between")
    public Collection<Student> findStudentByAgeBetween(@RequestParam int min,
                                                       @RequestParam int max) {
        return studentService.getByAgeBetween(min, max);
    }

    @GetMapping("/faculty-by-student-id")
    public Faculty getFacultyByStudentId(@RequestParam Long id) {
        return studentService.getFacultyByStudentId(id);
    }
}