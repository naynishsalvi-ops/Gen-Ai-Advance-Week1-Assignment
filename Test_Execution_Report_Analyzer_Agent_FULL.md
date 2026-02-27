
# Test Execution Report Analyzer Agent (TERAA)
## System Architecture & Technical Design Document

---

## 1. Overview

### System Name
Test Execution Report Analyzer Agent (TERAA)

### Purpose
Design and implement an AI-powered system to analyze Selenium Extent HTML execution reports, extract failure information, perform intelligent root cause analysis, and generate actionable failure analysis reports.

### Key Goals
- Automate failure triage
- Reduce debugging time
- Provide actionable insights
- Maintain human-in-the-loop governance
- Enable scalable AI-driven analysis

---

## 2. Input & Output Definition

### Input
- File Type: .html
- Source: Selenium + TestNG / JUnit / Cucumber (Extent Report)
- Upload Method: Web UI manual upload
- Test Volume: 100 – 500 test cases per report

### Output
Failure Analysis Report in tabular format (Web UI + downloadable PDF)

| Test Case ID | Execution Status | Failure Reason |
|--------------|------------------|----------------|
| TC_101       | FAIL             | Timeout while waiting for login button |

---

## 3. Functional Requirements

1. Upload HTML execution report via UI
2. Parse report to extract execution details
3. Identify failed test cases
4. Extract failure logs, stack traces, and steps
5. Perform AI-driven root cause classification
6. Generate structured failure analysis
7. Display results in UI
8. Generate downloadable PDF report
9. Store reports for 7–30 days

---

## 4. Non-Functional Requirements

| Category | Requirement |
|-----------|-------------|
| Performance | <2 minutes for 500 test cases |
| Scalability | Horizontal scaling |
| Reliability | Retry, fallback, and error handling |
| Maintainability | Modular micro-agent architecture |
| Security | Internal open access |
| Deployment | Hybrid (local + cloud) |

---

## 5. High-Level System Architecture

User → Web UI → FastAPI Backend → Multi-Agent AI Pipeline → PDF Generator → Storage

---

## 6. AI Multi-Agent Architecture

### Agent Pipeline

Parser Agent → Failure Extractor Agent → Root Cause Analyzer Agent → Report Generator Agent

---

## 7. Agent Responsibilities

### Parser Agent
- Parse HTML Extent report
- Extract test case ID, name, status, logs, steps, stack traces
- Convert HTML to structured JSON

### Failure Extractor Agent
- Filter failed test cases
- Normalize logs and error messages
- Prepare structured failure dataset

### Root Cause Analyzer Agent (LLM)
- Perform root cause classification
- Correlate logs + steps + stack traces
- Generate 2–3 line explanation

### Report Generator Agent
- Generate tabular output
- Build UI dataset
- Produce PDF report

---

## 8. Root Cause Classification Model

Base Categories:
- Locator Issue
- Timeout
- Data Issue
- Environment Issue
- Script Bug
- Infrastructure Failure
- Unknown

Approach:
Hybrid — deterministic parsing + AI reasoning

---

## 9. Technology Stack

Frontend: React / Next.js  
Backend: Python + FastAPI  
AI Orchestration: LangChain / LangGraph  
LLM: OpenAI / Azure OpenAI / Claude  
Database: PostgreSQL  
File Storage: Object Storage  
PDF Generation: ReportLab / WeasyPrint  
Deployment: Docker + Hybrid Cloud

---

## 10. Data Flow

1. User uploads report
2. Backend validates and stores file
3. Parser agent extracts execution data
4. Failure extractor isolates failed cases
5. Root cause agent performs AI analysis
6. Report generator builds UI + PDF
7. Results displayed and stored

---

## 11. AI Agent Master Prompt (Copilot)

SYSTEM ROLE:
You are a Test Execution Failure Analysis Agent specialized in Selenium automation debugging.

OBJECTIVE:
Analyze structured execution failure data extracted from HTML reports and generate accurate root cause explanations.

INPUT:
JSON containing:
- test_case_id
- failed_step
- error_message
- stack_trace
- execution_logs

TASK:
1. Classify failure into predefined categories.
2. Provide 2–3 line explanation.
3. Ensure determinism and avoid hallucination.

OUTPUT FORMAT:
{
  "test_case_id": "",
  "status": "FAIL",
  "failure_category": "",
  "failure_reason": ""
}

---

## 12. Database Design

### execution_reports

| Column | Type |
|----------|------|
| id | UUID |
| upload_time | Timestamp |
| filename | String |
| status | String |

### failure_results

| Column | Type |
|-----------|------|
| report_id | UUID |
| test_case_id | String |
| status | String |
| failure_reason | Text |
| category | String |

---

## 13. Deployment Architecture

Hybrid Model:
- Local: UI, Backend, Parsing
- Cloud: AI inference, orchestration, PDF generation

---

## 14. Scalability Strategy

- Async workers for parsing
- Horizontal scaling for AI agents
- Load balanced API services

---

## 15. Future Roadmap

Phase 1: HTML parsing + failure analysis  
Phase 2: Trend analytics dashboard  
Phase 3: Flaky test prediction  
Phase 4: CI/CD integration  
Phase 5: Self-healing automation suggestions  

---

## 16. Benefits

- 70–80% debugging effort reduction
- Faster root cause detection
- Standardized failure analysis
- Scalable enterprise-grade architecture

---

END OF DOCUMENT
