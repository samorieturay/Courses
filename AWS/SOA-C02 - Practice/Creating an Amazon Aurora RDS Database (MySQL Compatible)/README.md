# AWS Aurora MySQL Lab

This lab focused on setting up a secure and functional Amazon Aurora (MySQL Edition) environment within AWS, using best practices for networking and database configuration.

---

## Learning Objectives Completed

1. **Validate the Security Groups, Route Tables, and NACLs**
2. **Create a Database Subnet Group**
3. **Create an Amazon Aurora Cluster**
4. **Verify That You Can Authenticate to the Aurora Cluster**

---

## Environment Setup

- **Region:** `us-east-1`
- **Aurora Creation Method:** Manual (❌ _Easy Create was NOT used_)
- **Instance Class:** `db.t3.medium`
- **Aurora Cluster Identifier:** `AuroraLabCluster`
- **Subnet Group Name:** `AuroraLabSubnetGroup`

---

## Networking Configuration

### Subnet NACLs

- **Ports Allowed:** 22 (SSH), 3306 (MySQL)

### Route Tables

- **Private Subnets:** Only local routes
- **Public Subnet:** Route to internet gateway

### Security Groups

- One security group configured for **SSH**
- One security group configured for **MySQL**

---

## Database Subnet Group

- Created via the Amazon RDS dashboard
- Contains only **private subnets**
- Named `AuroraLabSubnetGroup`

---

## Amazon Aurora Cluster

- **Engine:** Aurora MySQL
- **Instance Type:** `db.t3.medium`
- **Configuration:**
  - Writer and reader nodes in different Availability Zones
  - Subnet group: `AuroraLabSubnetGroup`
  - Security group: MySQL security group provided by the lab

---

## Authentication Verification

Successfully authenticated to the Aurora cluster using the MySQL command line:

```sql
SHOW GLOBAL VARIABLES LIKE 'aurora_server_id';
```

Notes: Only showed write only because the reader instance isn't up and running. The writing instance can serve both read and writing request
