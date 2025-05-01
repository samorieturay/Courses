# AWS VPC Endpoint and S3 Bucket Lab

This lab demonstrates how to create an Amazon S3 bucket and a VPC Gateway Endpoint to allow private network access to S3 services without routing traffic over the public internet.

---

## Learning Objectives Completed

1. **Create an S3 Bucket**
2. **Create a VPC Endpoint**
3. **Verify VPC Endpoint Access to S3**

---

## Environment Setup

- **Region:** `us-east-1` (N. Virginia)
- **AWS IAM User:** `cloud_user`
- **Bucket Naming Convention:** `vpcendpointbucket-<random-numbers>` (to ensure uniqueness)

---

## S3 Bucket Configuration

- Created using the **AWS Management Console**
- Bucket name begins with `vpcendpointbucket` and includes random digits
- No public access or bucket policies were required for private access via VPC endpoint

---

## VPC Endpoint Configuration

- Navigated to **VPC Console**
- Located the unnamed **private route table** and renamed it to `private`
- Created a **Gateway VPC Endpoint** for the **S3 service**
- Selected the private subnet for association with the endpoint

---

## Access Verification

1. **Route Table Check:**

   - Verified that the private route table now includes a route to S3 via the new VPC endpoint

2. **SSH Validation Steps:**
   - Connected to a public EC2 instance
   - From the public instance, SSH’d into the **private EC2 instance**
   - Ran `aws s3 ls` and successfully listed the newly created S3 bucket from the private instance (without internet access)
