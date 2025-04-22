# Amazon RDS Multi-AZ and Read Replica

This lab focuses on deploying a fault-tolerant and scalable Amazon RDS setup using Multi-AZ deployment and Read Replicas. It also includes promoting a Read Replica to a standalone primary and updating DNS records using Route 53.

---

## Learning Objectives Completed

1. **Enable Multi-AZ Deployment**
2. **Create a Read Replica**
3. **Promote the Read Replica and Update Route 53 CNAME Record**

---

## Environment Setup

- **Region:** `us-east-1` (N. Virginia)
- **RDS Engine:** Amazon RDS (MySQL or PostgreSQL as per lab instructions)
- **Multi-AZ:** Enabled during primary RDS creation
- **Primary DB Instance Identifier:** `wordpress`
- **Read Replica Identifier:** `wordpress-rr`

---

## RDS Configuration

### Multi-AZ Deployment

- Enabled during the creation of the primary `wordpress` RDS instance
- Provides automatic failover for high availability

### Read Replica

- Created for the `wordpress` instance
- Named `wordpress-rr`
- Located in a different Availability Zone for load distribution

---

## Promotion & DNS Update

### Promote the Read Replica

- `wordpress-rr` was promoted to a standalone primary database instance

### Route 53 DNS Record Update

- The existing CNAME record in Route 53 was updated to point to the new primary database endpoint
- Ensures traffic is now directed to the newly promoted database

---

## Notes

- Always verify Multi-AZ deployment by checking Availability Zone distribution in the RDS dashboard
- Route 53 changes may take a few minutes to propagate depending on TTL settings
