package hackerthon.demo.controller;

import hackerthon.demo.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/picture")
public class PictureController {

    private final PictureService pictureService;


    /**
     * 1. upload
     * 2. flask로 message(uuid) 전송
     */
    @PostMapping("/uploads")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            // upload
            String fileName = pictureService.uploadFile(file);
            return ResponseEntity.ok(fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}