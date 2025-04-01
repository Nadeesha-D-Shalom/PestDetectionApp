# AutoRoverArduino

# Pest Detection Web Application

This is a simple Java-based web application for detecting and displaying pest activity data from a MySQL database. It uses Java Servlets and the Jetty server to handle HTTP requests and respond with real-time detection data in JSON format.

## 🚀 Features

- Displays the latest pest detection information (type and time)
- Connects to a MySQL database
- RESTful API using Java Servlets
- Hosted on an embedded Jetty server (port 8080)

## 📁 Project Structure

- `Main.java` – Starts the Jetty server and maps servlet routes.
- `DetectionServlet.java` – Handles GET/POST requests to fetch or simulate pest detection data.
- `DatabaseConnection.java` – Manages database connection settings.

## 🌐 API Endpoint

- `GET /detections` – Returns the most recent pest detection data in JSON format.
- `POST /detections` – Simulates a POST request response.

## 🛠 Technologies Used

- Java (Servlets)
- Jetty (embedded server)
- MySQL
- JDBC

## 🧠 How to Run

1. Make sure your MySQL database (`pest_detection_db`) is running and configured properly.
2. Compile the project using your preferred IDE or `javac`.
3. Run the `Main` class.
4. Open [http://localhost:8080/detections](http://localhost:8080/detections) in your browser or Postman.

---

### 📌 Note

Update the database credentials in `DatabaseConnection.java` to match your MySQL setup before running the app.

