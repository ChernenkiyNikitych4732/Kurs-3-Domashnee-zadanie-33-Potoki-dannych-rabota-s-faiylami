package sky.pro.course3.homework.hogwarts.school.ru.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.course3.homework.hogwarts.school.ru.model.Avatar;
import sky.pro.course3.homework.hogwarts.school.ru.model.Student;
import sky.pro.course3.homework.hogwarts.school.ru.repositories.AvatarRepository;
import sky.pro.course3.homework.hogwarts.school.ru.repositories.StudentRepository;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {


    private final StudentService studentService;
    private final AvatarRepository avatarRepository;
    private final String avatarsDir;

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService, @Value("${path.to.avatars.folder}") String avatarsDir) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
        this.avatarsDir = avatarsDir;
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentService.findStudent(studentId);
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = new Avatar();
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public ResponseEntity<byte[]> downloadAvatarByStudentFromDb(Long studentId) {

        Optional<Avatar> avatarOpt = avatarRepository.findByStudentId(studentId);

        if (avatarOpt.isEmpty()) {
            return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
        }

        Avatar avatar = avatarOpt.get();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    public void downloadAvatarFromFileSystem(Long studentId, HttpServletResponse response) throws IOException {

        Optional<Avatar> avatarOpt = avatarRepository.findByStudentId(studentId);

        if (avatarOpt.isEmpty()) {
            return;
        }

        Avatar avatar = avatarOpt.get();

        Path path = Path.of(avatar.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
}