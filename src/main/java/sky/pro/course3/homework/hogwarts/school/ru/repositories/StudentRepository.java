package sky.pro.course3.homework.hogwarts.school.ru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.course3.homework.hogwarts.school.ru.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge (int age);

    Collection<Student> findAllByAgeBetween(int min, int max);

    Collection<Student> findByFacultyId(Long facultyId);
}