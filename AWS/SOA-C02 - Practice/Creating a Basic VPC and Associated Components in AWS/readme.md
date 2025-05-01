# AWS VPC and Networking Fundamentals Lab

This lab demonstrates how to manually create a Virtual Private Cloud (VPC) from scratch, configure subnets in different availability zones, attach an internet gateway, and set up route tables and NACLs for both public and private access.

---

## Learning Objectives Completed

1. **Create a VPC**
2. **Create an Internet Gateway, and Attach It to the VPC**
3. **Create a Public and Private Subnet in Different Availability Zones**
4. **Create Two Route Tables, and Associate Them with the Correct Subnet**
5. **Create Two Network Access Control Lists (NACLs), and Associate Each with the Proper Subnet**

---

## Environment Setup

- **Region:** `us-east-1` (N. Virginia)
- **VPC Name:** `VPC1`
- **VPC CIDR:** `172.16.0.0/16`
- **Internet Gateway:** `IGW` (attached to `VPC1`)

---

## Subnet Configuration

### Public Subnet

- **Name:** `Public1`
- **AZ:** `us-east-1a`
- **CIDR:** `172.16.1.0/24`

### Private Subnet

- **Name:** `Private1`
- **AZ:** `us-east-1b`
- **CIDR:** `172.16.2.0/24`

---

## Route Tables

### Public Route Table

- **Name:** `PublicRT`
- **Associated Subnet:** `Public1`
- **Route:**
  - Destination: `0.0.0.0/0`
  - Target: `IGW` (Internet Gateway)

### Private Route Table

- **Name:** `PrivateRT`
- **Associated Subnet:** `Private1`
- **No external route to the internet** (private by design)

---

## Network ACLs (NACLs)

### PublicNACL

- **Associated Subnet:** `Public1`
- **Inbound Rules:**
  - Allow SSH (port 22) from anywhere
  - Allow HTTP (port 80) from anywhere
- **Outbound Rules:**
  - Allow ephemeral ports (`1024–65535`) to anywhere

### PrivateNACL

- **Associated Subnet:** `Private1`
- **Inbound Rules:**
  - Allow SSH (port 22) from `172.16.1.0/24` only (internal admin access)
- **Outbound Rules:**
  - Allow ephemeral ports (`1024–65535`) to anywhere
