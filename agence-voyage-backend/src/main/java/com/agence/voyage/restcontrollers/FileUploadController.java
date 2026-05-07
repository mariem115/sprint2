package com.agence.voyage.restcontrollers;

import com.agence.voyage.entities.Voyage;
import com.agence.voyage.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private VoyageService voyageService;

    @PostMapping("/uploadImage/{id}")
    public Voyage uploadImage(@PathVariable Long id,
                              @RequestParam("file") MultipartFile file) throws IOException {
        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        String original = file.getOriginalFilename() == null ? "image" : file.getOriginalFilename();
        String filename = id + "_" + original.replaceAll("\\s+", "_");
        Path filePath = Paths.get(uploadDir + filename);
        Files.write(filePath, file.getBytes());

        Voyage voyage = voyageService.getVoyage(id);
        if (voyage == null) {
            throw new IOException("Voyage not found: " + id);
        }
        voyage.setImageUrl("/agence/images/" + filename);
        return voyageService.updateVoyage(voyage);
    }

    @DeleteMapping("/uploadImage/{id}")
    public Voyage deleteImage(@PathVariable Long id) throws IOException {
        Voyage voyage = voyageService.getVoyage(id);
        if (voyage == null) {
            throw new IOException("Voyage not found: " + id);
        }
        if (voyage.getImageUrl() != null) {
            String prefix = "/agence/images/";
            String path = voyage.getImageUrl();
            if (path.startsWith(prefix)) {
                Path filePath = Paths.get("uploads/" + path.substring(prefix.length()));
                Files.deleteIfExists(filePath);
            }
            voyage.setImageUrl(null);
        }
        return voyageService.updateVoyage(voyage);
    }
}
