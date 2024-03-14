package backend.course.spring.uplasthackathon.service;

import backend.course.spring.uplasthackathon.exception.EmptyFileException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;

@Service
public class ImageUploadService {

    @SneakyThrows
    public String saveImage(MultipartFile multipartfile) {
        if (multipartfile.isEmpty()) {
            throw new EmptyFileException("File is empty");
        }

        final String urlKey = "cloudinary://429488699555469:ikzaa8wUnIvSZJr7h917mJPTdKU@dovfdzzuz";


        Cloudinary cloudinary = new Cloudinary((urlKey));

        File saveFile = Files.createTempFile(
                        System.currentTimeMillis() + "",
                        Objects.requireNonNull
                                (multipartfile.getOriginalFilename(), "File must have an extension")
                )
                .toFile();

        multipartfile.transferTo(saveFile);


        Map upload = cloudinary.uploader().upload(saveFile, ObjectUtils.emptyMap());

        return (String) upload.get("url");
    }
}
