package sky.pro.course3.homework.hogwarts.school.ru.generator;

import sky.pro.course3.homework.hogwarts.school.ru.model.Student;

public class StudentGenerator {

    public static final String NAME = "Игорь";
    public static final int AGE = 17;

    public static Student getStudent() {
        return new Student(NAME, AGE);
    }
}