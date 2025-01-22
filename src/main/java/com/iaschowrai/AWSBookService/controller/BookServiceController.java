package com.iaschowrai.AWSBookService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/bookservice/api")
public class BookServiceController {

    public ResponseEntity<String> upload(@RequestParam("image")MultipartFile imager, @RequestParam String isbn,
                                         @RequestParam String bookName, @RequestParam String authorName,
                                         @RequestParam int yearPublished){
        return null;
    }
}
