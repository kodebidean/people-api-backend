package com.imamultidev.api_personas.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;


@Service
@ConditionalOnProperty(name = "cloudinary.enabled", havingValue = "true", matchIfMissing = true)
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String, Object> uploadImage(MultipartFile file) throws IOException {
        if (cloudinary == null) {
            throw new IllegalStateException("Cloudinary no está configurado");
        }
        return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
            "use_filename", true,
            "unique_filename", false,
            "overwrite", true
        ));
    }

    public void deleteImage(String publicId) throws IOException {
        if (cloudinary == null) {
            throw new IllegalStateException("Cloudinary no está configurado");
        }
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}