# Redshift Data Import from S3 and DynamoDB (Self-Practice)

This document outlines the steps I took to practice importing data into an Amazon Redshift cluster from both an S3 bucket and a DynamoDB table. The goal was to simulate a real-world request from a development team seeking secure, efficient import options for structured and semi-structured data.

---

## Tasks Completed

1. **Configured AWS CLI on a Cloud Server**
2. **Prepared Source Data (CSV for S3, JSON for DynamoDB)**
3. **Created IAM Role for Redshift with Read Access to S3 and DynamoDB**
4. **Imported Data into Redshift from Both S3 and DynamoDB**

---

## Environment Setup

- **Region:** `us-east-1`
- **Redshift Cluster User:** `masteruser`
- **Password:** `MasterPasswd2020!`
- **Redshift Database:** `import-test`
- **Table Name:** `users_import`
- **S3 Bucket Name:** `redshift-import-<unique-id>`
- **DynamoDB Table Name:** `redshift-import`
- **IAM Role Name:** `redshift-import`

---

## Summary of Steps

### 1. Configure AWS CLI

- Created an IAM access key and secret key for the session
- Ran `aws configure` to set up CLI on the EC2 instance

### 2. Prepare and Upload Source Data

- Downloaded CSV (`redshift-data.csv`) and JSON (`redshift-data.json`) files from GitHub
- Created a unique S3 bucket and uploaded the CSV file
- Created a DynamoDB table named `redshift-import` and loaded the JSON data

### 3. Create and Attach IAM Role

- Created IAM role `redshift-import` with:

  - `AmazonS3ReadOnlyAccess`
  - `AmazonDynamoDBReadOnlyAccess`

- Updated trust relationship to allow `redshift.amazonaws.com`
- Attached this role to the Redshift cluster

### 4. Load Data into Redshift

- Connected to Redshift from the EC2 terminal using `psql`
- Created the `users_import` table
- Imported CSV data from S3 using the `COPY` command
- Queried and verified imported records
- Truncated the table and imported data again—this time from DynamoDB
- Verified the final insert was successful

---

## Sample SQL Used

```sql
-- Create table
CREATE TABLE users_import (
  ID INT,
  Name VARCHAR,
  Department VARCHAR,
  ExpenseCode INT
);

-- Load from S3
COPY users_import
FROM 's3://<BUCKET_NAME>/redshift-data.csv'
IAM_ROLE '<IAM_ROLE_ARN>'
DELIMITER ',';

-- Load from DynamoDB
COPY users_import
FROM 'dynamodb://redshift-import'
IAM_ROLE '<IAM_ROLE_ARN>'
READRATIO 50;
```

---

## Notes

- S3 is ideal for loading structured, bulk data into Redshift using CSV or Parquet
- DynamoDB import is better suited for smaller, NoSQL-style datasets and may require preprocessing
- Always truncate the target table before reloading with a different dataset source to avoid duplication
