package sky.pro.course3.homework.hogwarts.school.ru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.course3.homework.hogwarts.school.ru.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByColorIgnoreCase(String color);

    Collection<Faculty> findByNameIgnoreCase(String name);
}