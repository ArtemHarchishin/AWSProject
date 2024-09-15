package com.akharchyshyn.aws.service;

import com.akharchyshyn.aws.dto.ImageUploadDTO;
import org.springframework.http.ResponseEntity;

public interface AWSImageService {

    ResponseEntity<?> uploadImage(ImageUploadDTO dto);

    ResponseEntity<?> downloadImage(String fileName);

    ResponseEntity<?> getImagesInfo(String fileName, Boolean randomEnable);

    ResponseEntity<?> invokeImageLambda();

}
