package com.example.demo.controller;

import com.example.demo.model.File;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping
    public Iterable<File> read() {
        return fileService.getAllFiles();
    }

    @PostMapping
    public File save(@RequestBody File file) {
        return fileService.saveFile(file);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return fileService.deleteFile(id);
    }

}
