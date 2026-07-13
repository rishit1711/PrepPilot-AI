# 🚀 PrepPilot AI

> **An Adaptive AI Interview Intelligence Platform** that helps candidates prepare for technical interviews through personalized document analysis, Retrieval-Augmented Generation (RAG), adaptive mock interviews, resume claim verification, and evidence-based feedback.

> ⚠️ **Project Status:** Currently under active development.

---

## 📖 Overview

PrepPilot AI is a backend-first AI platform designed to simulate realistic technical interviews instead of acting as just another chatbot.

The platform analyzes resumes, job descriptions, and supporting documents to generate personalized interview experiences, verify claimed skills, evaluate responses with explainable feedback, and recommend a customized learning roadmap.

---

## ✨ Planned Features

- 🔐 JWT Authentication & User Management
- 📄 Resume & Job Description Analysis
- 📚 Knowledge Vault powered by RAG
- 🤖 Adaptive AI Interview Engine
- ✅ Resume Claim Verification
- 💡 Hint Mode (without revealing answers)
- 📊 Evidence-Based Evaluation
- 📑 Downloadable Interview Reports
- 📈 Interview Timeline & Progress Tracking
- 🎯 Personalized Learning Roadmap

---

## 🛠️ Tech Stack

### Backend
- Spring Boot
- Spring Security
- JWT Authentication
- Spring AI / LangChain4j

### Database
- PostgreSQL
- Qdrant (Vector Database)

### AI
- Gemini API 
- Retrieval-Augmented Generation (RAG)

### Frontend
- Lovable (React + Tailwind)

### DevOps
- Docker
- Render / Railway
- AWS *(Planned)*

---

## 🏗️ Architecture

```text
Lovable Frontend
        │
        ▼
 Spring Boot Backend
        │
 Controllers
        │
 Services
        │
 AI Orchestrator
        │
 Prompt Builder
        │
Retriever (Qdrant)
        │
 Gemini API
        │
PostgreSQL + Vector DB
```

---

## 🎯 Current Development Roadmap

- [x] Project Planning & Architecture
- [ ] Authentication & User Management
- [ ] Knowledge Vault (RAG)
- [ ] Resume Analyzer
- [ ] JD Match Analyzer
- [ ] Interview Blueprint
- [ ] Adaptive Interview Engine
- [ ] Resume Claim Verification
- [ ] Evidence-Based Evaluation
- [ ] Interview Reports
- [ ] Learning Roadmap
- [ ] Docker & Deployment

---

## 🌟 What Makes It Different?

Unlike traditional interview preparation platforms, PrepPilot AI focuses on:

- Adaptive interview flow based on candidate performance
- Resume claim verification instead of random questioning
- Explainable AI evaluation with evidence-backed feedback
- Personalized interview blueprints
- Continuous learning through progress tracking and AI-generated roadmaps

---

## 📌 Project Status

🚧 This project is currently under active development. Features and architecture will continue to evolve as development progresses.

---

## 📄 License

This project is intended for educational and portfolio purposes.
