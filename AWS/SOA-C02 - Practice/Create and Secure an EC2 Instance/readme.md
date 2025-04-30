# Launch and Secure an Amazon EC2 Instance (My-IP Only)

This lab focuses on deploying a secure Amazon EC2 instance that is only accessible from your personal machine. It walks through creating a key pair, configuring a security group with restricted SSH access, and testing connectivity from different sources.

---

## Learning Objectives Completed

1. **Configure a Security Group**
2. **Create an EC2 Instance**
3. **Test Access to the EC2 Instance**

---

## Environment Setup

- **Region:** `us-east-1` (N. Virginia)
- **Instance Type:** `t3.micro`
- **AMI:** Amazon Linux 2
- **Key Pair Name:** `keypair1`
- **Security Group Name:** `SG1`
- **Instance Tag:** `Name = My Instance`

---

## EC2 and Security Group Configuration

### Security Group: SG1

- **Inbound Rule:**
  - Protocol: SSH (Port 22)
  - Source: `My IP` only
- **Purpose:** Restrict access to the EC2 instance to your current IP address only

### EC2 Instance Details

- **Instance Type:** `t3.micro`
- **Key Pair:** `keypair1` (downloaded and saved locally)
- **Networking:** Public IP assigned automatically
- **Attached Security Group:** `SG1`
- **Tags:**
  - Key: `Name`
  - Value: `My Instance`

---

## Access Verification

- **Access from your machine:** Successful via SSH using `keypair1`
- **Access from other EC2 instances:** Denied due to IP-based restrictions in security group

---

## Notes

- Ensure your IP address is up-to-date when using the “My IP” option (especially if on a dynamic IP)
- Private key file (`keypair1.pem`) must have secure permissions (`chmod 400`)
- Test from another instance validates that security group rules are effectively applied
