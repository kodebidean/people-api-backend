package com.imamultidev.api_personas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imamultidev.api_personas.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}