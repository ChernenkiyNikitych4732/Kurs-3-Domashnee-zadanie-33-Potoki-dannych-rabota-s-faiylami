package sky.pro.course3.homework.hogwarts.school.ru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.course3.homework.hogwarts.school.ru.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository <Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
}