# AWS Tags and Resource Groups

This document summarizes a hands-on practice session where I explored how to use AWS tags and resource groups to organize and manage resources in a shared AWS account. The goal was to make it easier to identify project-specific components and monitor them more efficiently.

---

## Tasks Completed

1. **Created and Assigned Project-Level Tags to Resources**
2. **Built Resource Groups Based on Tags**
3. **Verified Resource Groups Using AWS CloudWatch**

---

## Environment Setup

- **Region:** `us-east-1` (N. Virginia)
- **Tag Key Used:** `Project`
- **Tag Values:** `Star`, `Big`
- **AWS Services Used:** EC2, S3, Tag Editor, Resource Groups, CloudWatch

---

## Tagging Resources

- Opened **Resource Groups & Tag Editor > Tag Editor**
- Chose region: `us-east-1`
- Filtered for:
  - `AWS::EC2::Instance`
  - `AWS::S3::Bucket`
- Tagged relevant resources:
  - Assigned `Project=Star` to all resources related to "Star"
  - Assigned `Project=Big` to all resources related to "Big"

---

## Creating Resource Groups

- Went to **Resource Groups & Tag Editor > Create Resource Group**
- Chose **Tag-based** grouping method
- Used `Project` tag to create:
  - `Big-Project-Resource-Group`
  - `Star-Project-Resource-Group`
- Included all supported resource types in each group

---

## CloudWatch Verification

- Opened **CloudWatch > Overview**
- Used the **Filter by resource group** option to:
  - View metrics for `Big-Project-Resource-Group`
  - View metrics for `Star-Project-Resource-Group`

---

## Notes

- This approach supports better visibility and cost tracking across projects
- Tag-based resource grouping improves automation and filtering in larger environments
- CloudWatch integration allows real-time monitoring of grouped resources
