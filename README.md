# 📊 Real-time Log Processing System

A **Java + Spring Boot** project that simulates and processes logs in real time.  
The system provides live insights (error rates, latency, endpoint usage) and a dashboard with charts + live log table.  

🚀 Perfect project for **Backend + System Design + Concurrency** experience.  

---

## 🎯 Features

- ✅ Real-time log ingestion (simulated log streams)  
- ✅ Concurrent processing using **Java Streams + CompletableFuture**  
- ✅ REST APIs for metrics and insights  
- ✅ Interactive dashboard (HTML, CSS, JS + Chart.js)  
- ✅ **Live updates** with WebSockets (no polling)  
- ✅ **Dark/Light mode toggle**  
- ✅ Search + filter logs by **message, endpoint, or level**  
- ✅ Export logs to **CSV**  
- ✅ Database persistence using **MySQL**  

---

## ⚙️ Tech Stack

- **Backend**: Java 17, Spring Boot, Spring Web, Spring Data JPA, Spring WebSocket  
- **Database**: MySQL  
- **Frontend**: HTML, CSS, JavaScript, Chart.js  
- **Other**: Lombok, Actuator (monitoring), Swagger/OpenAPI (API docs)  

---

## 📂 Project Structure

src/main/java/com/example/logprocessor
├── controller # REST APIs
├── service # Business logic
├── repository # JPA repositories
├── model # Entities (LogEntry, etc.)
├── websocket # WebSocket config + broadcasting

yaml
Copy code

---

## 🔌 REST APIs

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/stats/errors` | GET | Get error counts by level |
| `/api/stats/errors/recent?minutes=X` | GET | Error count in last X minutes |
| `/api/stats/latency/recent?minutes=X` | GET | Average latency in last X minutes |
| `/api/stats/endpoints/recent?minutes=X` | GET | Top endpoints usage in last X minutes |
| `/api/stats/logs/recent?minutes=X` | GET | Get recent logs |

---

## 📡 WebSocket Endpoint

- **Endpoint**: `/ws`  
- **Subscription**: `/topic/logs`  
- **Payload**: `LogEntry` JSON object  

Frontend automatically receives logs instantly without refresh.  

---

## 🖥️ Dashboard (UI)

- Built with HTML + CSS + JS  
- **Charts**: Error distribution, Latency, Endpoints (via Chart.js)  
- **Table**: Live logs (search + filter by ERROR/INFO/WARN)  
- **Extras**:  
  - 🌗 Dark/Light theme toggle  
  - ⬇️ Export logs as CSV  

---

## ⚙️ Setup & Run

### 1. Clone repo
```bash
git clone https://github.com/your-username/log-processor.git
cd log-processor
2. Configure MySQL
Create a database logsdb in MySQL:
```
## 2. Sql
```bash
CREATE DATABASE logsdb;
```
## Update 

# src/main/resources/application.properties:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/logsdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
## 3. Build & Run
```bash
mvn spring-boot:run
```
### Backend runs on http://localhost:8080.

## Open dashboard.html in browser → Live dashboard! 🎉



## 🚀 Future Improvements
✅ Kafka/RabbitMQ for real distributed log ingestion

✅ Dockerize app for deployment

✅ Add authentication (Spring Security + JWT)

✅ Advanced analytics (percentiles, trends, anomaly detection)

## 👨‍💻 Author
Developed by Harshal Thakare

🎓 B.Tech Final Year Student

💼 Interested in Backend Engineering, Distributed Systems & Cloud
