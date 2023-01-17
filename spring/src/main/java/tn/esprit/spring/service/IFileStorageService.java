package tn.esprit.spring.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileStorageService {
    public void deleteAll();
    public Stream<Path> loadAll();
    public Resource load(String filename);
    public void save(MultipartFile file);
    public void init();
}
