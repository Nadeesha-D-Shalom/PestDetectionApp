package com.pestdetection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        try {
            // Get the database connection
            Connection conn = DatabaseConnection.getConnection();
            // Define the SQL query to fetch the most recent detection data
            String query = "SELECT detection_type, detection_time FROM detections ORDER BY detection_time DESC LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Start building the JSON object
            StringBuilder jsonResult = new StringBuilder();

            if (rs.next()) {
                jsonResult.append("{");
                jsonResult.append("\"detection_type\":\"").append(rs.getString("detection_type")).append("\",");
                jsonResult.append("\"detection_time\":\"").append(rs.getString("detection_time")).append("\"");
                jsonResult.append("}");
            } else {
                jsonResult.append("{\"error\":\"No data found\"}"); // Ensure this is a valid JSON object
            }

            // Output the JSON result
            out.print(jsonResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"error\": \"Error fetching data from database\"}");
        } finally {
            out.flush(); // Ensure that data is sent
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String jsonResponse = "{\"status\":\"success\",\"message\":\"Pest detection POST request is handled!\"}";
        PrintWriter out = resp.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
