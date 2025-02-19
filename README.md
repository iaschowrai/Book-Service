# AWS Book Service

This project is an AWS cloud-based Book Service application that leverages several AWS services to manage book-related data efficiently.
The Book Service with AWS repository is designed to showcase how to build a cloud-native microservice that efficiently manages book-related data using AWS services. 
It demonstrates real-world use cases of integrating AWS with a Spring Boot application, making it a valuable resource for developers who want to learn cloud-based development, 
AWS service integration, and microservices architecture.

## Features

- **File Storage**: Utilizes Amazon S3 buckets for storing and retrieving book files.
- **Message Queuing**: Employs Amazon SQS for message queuing and pub/sub communication.
- **Data Storage**: Uses Amazon DynamoDB for scalable and fast data storage and retrieval.

## Prerequisites

Before running the application, ensure you have the following:

- Java Development Kit (JDK) 17 or higher
- Maven 3.6.0 or higher
- AWS account with access to S3, SQS, and DynamoDB services
- AWS CLI configured with appropriate credentials

## Getting Started

1. **Clone the Repository**:

    ```bash
   git clone https://github.com/iaschowrai/book-service-with-aws.git
   cd book-service-with-aws
   ```


2. **Build the Application**:

    ```bash
   ./mvnw clean install
   ```


3. **Configure AWS Resources**:

   - **S3 Bucket**: Create an S3 bucket to store book files.
   - **SQS Queue**: Set up an SQS queue for message handling.
   - **DynamoDB Table**: Create a DynamoDB table to store book metadata.

    Ensure that your AWS credentials have the necessary permissions to access these resources.  

4. **Run the Application**:

    ```bash
   ./mvnw spring-boot:run
   ```


    The application will start and connect to the configured AWS services.  

## Configuration

Configuration settings, such as AWS credentials and resource names, can be managed in the `application.properties` file located in the `src/main/resources` directory. Ensure that this file is properly configured before running the application.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License.

---

For more information, visit the [repository](https://github.com/iaschowrai/book-service-with-aws). 
