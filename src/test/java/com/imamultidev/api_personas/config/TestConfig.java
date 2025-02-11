package com.imamultidev.api_personas.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import com.cloudinary.Cloudinary;
import org.mockito.Mockito;

@TestConfiguration
public class TestConfig {
    
    @Bean
    @Primary
    public Cloudinary cloudinary() {
        return Mockito.mock(Cloudinary.class);
    }
} 