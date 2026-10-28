package com.student.servlet;

import com.student.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String rollNo = request.getParameter("roll_no");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        int semester = Integer.parseInt(request.getParameter("semester"));

        String sql = "INSERT INTO Students "
                   + "(roll_no, name, email, course, semester) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rollNo);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, course);
            ps.setInt(5, semester);

            ps.executeUpdate();

            out.println("<h2>Student registered successfully!</h2>");

        } catch (Exception e) {
            out.println("<h2>Unable to register student.</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
        }
    }
}