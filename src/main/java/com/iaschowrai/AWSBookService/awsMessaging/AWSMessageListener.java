package com.iaschowrai.AWSBookService.awsMessaging;

import com.iaschowrai.AWSBookService.configuration.ApplicationProperties;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AWSMessageListener {

    private final ApplicationProperties applicationProperties;

    @SqsListener(queueNames = {"${book-service.queue}"})
    public void handleMessage(String message){
        log.info(message);
    }
}
