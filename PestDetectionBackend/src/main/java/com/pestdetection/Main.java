package com.pestdetection;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create a Jetty server that listens on port 8080
        Server server = new Server(8080);

        // Create a handler to manage the servlets
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        // Add the DetectionServlet to handle /detections endpoint
        handler.addServlet(new ServletHolder(new DetectionServlet()), "/detections");

        // Attach the handler to the server
        server.setHandler(handler);

        // Start the server
        server.start();
        System.out.println("Server is running on http://localhost:8080/detections");

        // Keep the server running
        server.join();
    }
}
