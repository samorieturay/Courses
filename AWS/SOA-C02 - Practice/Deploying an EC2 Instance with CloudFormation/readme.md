# AWS CloudFormation EC2 Deployment with Apache Setup

In this lab, you assume the role of a developer tasked with modifying and deploying a CloudFormation template to launch an EC2 instance. You'll configure networking, install Apache via user data, and validate the deployment by loading a custom Apache test page.

---

## Learning Objectives Completed

1. **Specify the AMI and Instance Type and Deploy an EC2 Instance**
2. **Update the Template to Deploy the EC2 in the Correct Network with a Custom Security Group**
3. **Update and Redeploy an EC2 Instance with a User Data Script to Install Apache**
4. **Verify the Apache Test Page Loads**

---

## Environment Setup

- **Region:** `us-east-1` (N. Virginia)
- **CloudFormation Template Source:** GitHub
- **Base AMI:** Amazon Linux 2023
- **Instance Type:** Parameterized (`t3.micro` used for testing)
- **VPC & Subnet:**
  - VPC: `ACG_VPC`
  - Subnet: `ACG_PUBLIC_1`

---

## CloudFormation Template Customizations

### Parameters Section

- Added parameter to select **EC2 Instance Type**
- Hardcoded Amazon Linux 2023 **AMI ID** in appropriate mapping or parameter

### Networking Configuration

- Deployed EC2 in existing VPC `ACG_VPC`
- Attached to subnet `ACG_PUBLIC_1`
- Created custom **Security Group** with:
  - Port 22 (SSH) open to `My IP`
  - Port 80 (HTTP) open to the world (`0.0.0.0/0`)

---

## EC2 Configuration

- **User Data Script** added for:
  - Installing Apache (`httpd`)
  - Starting and enabling Apache on boot
  - Writing EC2 metadata (e.g., instance ID, IPs, AZ) to `/var/www/html/index.html`

### Sample Snippet Used:

```yaml
UserData:
  Fn::Base64: !Sub |
    #!/bin/bash
    yum install -y httpd
    systemctl start httpd
    systemctl enable httpd
    # Additional metadata logic omitted for brevity
```

---

## Validation

- Accessed public IP of the deployed EC2 instance
- Apache default/index page successfully loaded, confirming:
  - HTTP access is working
  - User data script ran as expected
