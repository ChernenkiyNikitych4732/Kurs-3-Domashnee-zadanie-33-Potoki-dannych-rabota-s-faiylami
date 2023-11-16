package sky.pro.course3.homework.hogwarts.school.ru.service;

import org.springframework.stereotype.Service;
import sky.pro.course3.homework.hogwarts.school.ru.model.Faculty;
import sky.pro.course3.homework.hogwarts.school.ru.model.Student;
import sky.pro.course3.homework.hogwarts.school.ru.repositories.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student deleteStudent(long id) {
        Student studentForDelete = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return studentForDelete;
    }

    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Faculty getFacultyByStudentId(Long id) {
        return studentRepository.findById(id).get().getFaculty();
    }

    public Collection<Student> getByFacultyId(Long facultyId) {
        return studentRepository.findByFacultyId(facultyId);
    }
}