package com.imamultidev.api_personas.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.imamultidev.api_personas.model.Image;
import com.imamultidev.api_personas.repository.ImageRepository;
import com.imamultidev.api_personas.service.CloudinaryService;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    public ImageController(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> uploadResult = cloudinaryService.uploadImage(file);        
        String url = (String) uploadResult.get("secure_url");
        String publicId = (String) uploadResult.get("public_id");

        Image image = new Image();
        image.setUrl(url);
        image.setPublicId(publicId);
        imageRepository.save(image);

        return ResponseEntity.ok(image);
    }

    @GetMapping
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) throws IOException {
        Image image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
        cloudinaryService.deleteImage(image.getPublicId());
        imageRepository.delete(image);
        return ResponseEntity.noContent().build();
    }
}
