package com.iaschowrai.AWSBookService.controller;

import com.iaschowrai.AWSBookService.awsMessaging.AWSMessageSender;
import com.iaschowrai.AWSBookService.configuration.ApplicationProperties;
import com.iaschowrai.AWSBookService.db.BookDynamoDbRepository;
import com.iaschowrai.AWSBookService.db.S3StorageService;
import com.iaschowrai.AWSBookService.model.BookServiceModel;
import io.awspring.cloud.s3.S3Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/book-service/api")
@RequiredArgsConstructor
public class BookServiceController {

    private final S3StorageService s3StorageService;
    private final BookDynamoDbRepository bookDynamoDbRepository;
    private final AWSMessageSender awsMessageSender;
    private final ApplicationProperties applicationProperties;

    @PostMapping("upload")
    public ResponseEntity<String> upload(@RequestParam("image")MultipartFile image, @RequestParam String isbn,
                                         @RequestParam String bookName, @RequestParam String authorName,
                                         @RequestParam int yearPublished){
        try{
            S3Resource s3Resource = s3StorageService.upload(image, bookName);
            BookServiceModel book = BookServiceModel.builder()
                    .id(UUID.randomUUID()).bookName(bookName)
                    .authorName(authorName).isbn(isbn)
                    .imageUrl(s3Resource.getURL().toString()).yearPublished(yearPublished)
                    .build();

            bookDynamoDbRepository.save(book);
            awsMessageSender.publish(applicationProperties.queue(),book);
            return new ResponseEntity<>(String.format("{'id':'%s', 'imageUrl': '%s'}", book.getId(),
                    book.getImageUrl()), HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<String>(String.format("{'errorMessage': '%s'}",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
