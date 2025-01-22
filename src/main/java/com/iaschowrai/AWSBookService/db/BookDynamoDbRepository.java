package com.iaschowrai.AWSBookService.db;

import com.iaschowrai.AWSBookService.model.BookServiceModel;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDynamoDbRepository {
    private final DynamoDbTemplate dynamoDbTemplate;

    public void save(BookServiceModel book){
        dynamoDbTemplate.save(book);
    }

    public List<BookServiceModel> findAll(){
        return dynamoDbTemplate.scanAll(BookServiceModel.class).items().stream().toList();
    }

}
