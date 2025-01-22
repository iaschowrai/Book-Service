/*
This Terraform file defines the infrastructure for a book service using AWS resources. It sets up an S3 bucket,
DynamoDB table, and an SQS queue, with configurations specific to LocalStack (a local AWS cloud service emulator).
The file also includes policies and access control settings for each resource.
 */

# Required providers block specifies the AWS provider and its version
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"    # Source for AWS provider
      version = "5.54.1"           # Specify the required AWS provider version
    }
  }
}

# Configure the AWS provider with custom settings, such as access keys and endpoints
provider "aws" {
  region                    = "us-east-1"               # Specify the AWS region
  access_key                = "test"                     # AWS access key (for LocalStack testing)
  secret_key                = "test"                     # AWS secret key (for LocalStack testing)
  skip_credentials_validation = true                     # Skip credentials validation (for LocalStack)
  skip_requesting_account_id = true                      # Skip account ID fetching (for LocalStack)
  s3_use_path_style         = true                      # Use path-style URLs for S3 (LocalStack compatibility)
  endpoints {                                           # Custom endpoints for LocalStack emulation
    s3      = "http://localhost:4566"
    sqs     = "http://localhost:4566"
    dynamodb = "http://localhost:4566"
  }
}

# Create an S3 bucket named "book-bucket"
resource "aws_s3_bucket" "book_bucket" {
  bucket = "book-bucket"  # Name of the S3 bucket
}

# Set the ACL for the "book-bucket" to "public-read-write"
resource "aws_s3_bucket_acl" "s3_bucket" {
  bucket = aws_s3_bucket.book_bucket.id   # Reference to the created S3 bucket ID
  acl    = "public-read-write"            # Set ACL to allow public read and write
}

# Define a bucket policy that allows public read access to the S3 objects in the bucket
resource "aws_s3_bucket_policy" "s3_bucket_policy" {
  bucket = aws_s3_bucket.book_bucket.id  # Reference to the created S3 bucket ID
  policy = jsonencode({                 # Define the JSON bucket policy
    Version = "2012-10-17",
    Statement = [
      {
        Sid       = "publicReadGetObject", # Statement ID
        Effect    = "Allow",                # Allow the action
        Principal = "*",                    # Allow everyone (public access)
        Action    = "s3.GetObject",         # Action: Get object from S3
        Resource = [
          "${aws_s3_bucket.book_bucket.arn}/*",  # Reference all objects in the bucket
        ],
      }
    ],
  })
}

# Create a DynamoDB table named "book" with read and write capacity
resource "aws_dynamodb_table" "book_table" {
  name           = "book"             # Table name
  read_capacity  = "2"                # Read capacity units
  write_capacity = "5"                # Write capacity units
  hash_key       = "id"               # Define the hash key (primary key)

  # Define the table's attributes (e.g., 'id' as string type)
  attribute {
    name = "id"
    type = "S"   # String type for the "id" attribute
  }
}

# Create an SQS queue named "book-queue"
resource "aws_sqs_queue" "queue" {
  name = "book-queue"  # Name of the SQS queue
}
