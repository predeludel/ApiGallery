package com.example.demo.service;

import com.example.demo.model.Base;
import com.example.demo.model.File;
import com.example.demo.repo.BaseRepository;
import com.example.demo.repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private BaseRepository baseRepository;

    public Iterable<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public boolean deleteFile(Long id) {
        if (fileRepository.existsById(id)) {
            fileRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public File saveFile(File file) {
        if (fileRepository.findById(file.getId()).isPresent()) {
            File oldFile = fileRepository.findById(file.getId()).get();
            file.setBase(oldFile.getBase());
            file.getBase().setUpdateDateTime(LocalDateTime.now());
        } else {
            Base base = new Base(LocalDateTime.now(), LocalDateTime.now());
            base = baseRepository.save(base);
            file.setBase(base);
        }
        return fileRepository.save(file);

    }
}
