package com.example.demo.service;

import com.example.demo.model.Image;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ResizingService {

    @Value("${upload.path}")
    private String uploadPath;


    public void createImage(File file, int width,int height, int count) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            BufferedImage outputImage = Scalr.resize(bufferedImage, width,height);
            String newFileName = FilenameUtils.getBaseName(file.getName())
                    + "_" + count + "."
                    + FilenameUtils.getExtension(file.getName());
            Path path = Paths.get(uploadPath, newFileName);
            File newImageFile = path.toFile();
            ImageIO.write(outputImage, "png", newImageFile);
            outputImage.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void resizeImage(Image image) {
        try {
        File file = new File(image.getAttachFilePath());
        BufferedImage bufferedImage = ImageIO.read(file);
        int div = 2;
        for(int i = 0; i<3; i++){
            createImage(file,bufferedImage.getWidth()/div, bufferedImage.getHeight()/div, i);
            div*=2;
        }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }
}