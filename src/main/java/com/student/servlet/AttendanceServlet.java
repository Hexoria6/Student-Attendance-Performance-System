package com.student.servlet;

import com.student.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Mark Attendance</title></head>");
        out.println("<body style='font-family:Arial;margin:30px'>");
        out.println("<h2>Student Attendance</h2>");
        out.println("<form method='post' action='AttendanceServlet'>");
        out.println("Student: <select name='student_id' required>");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT student_id, roll_no, name FROM Students ORDER BY student_id");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                out.println("<option value='" + rs.getInt("student_id") + "'>"
                        + rs.getString("roll_no") + " - "
                        + rs.getString("name") + "</option>");
            }

        } catch (Exception e) {
            out.println("</select><p>Unable to load students.</p>");
            e.printStackTrace();
        }

        out.println("</select><br><br>");
        out.println("Date: <input type='date' name='attendance_date' required><br><br>");
        out.println("Status: <select name='status' required>");
        out.println("<option value='Present'>Present</option>");
        out.println("<option value='Absent'>Absent</option>");
        out.println("<option value='Late'>Late</option>");
        out.println("</select><br><br>");
        out.println("<button type='submit'>Save Attendance</button>");
        out.println("</form></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String sql = "INSERT INTO Attendance "
                   + "(student_id, attendance_date, status) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(request.getParameter("student_id")));
            ps.setDate(2, java.sql.Date.valueOf(
                    request.getParameter("attendance_date")));
            ps.setString(3, request.getParameter("status"));

            ps.executeUpdate();

            out.println("<h2>Attendance saved successfully!</h2>");
            out.println("<a href='AttendanceServlet'>Mark More Attendance</a>");

        } catch (Exception e) {
            out.println("<h2>Unable to save attendance.</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<a href='AttendanceServlet'>Go Back</a>");
        }
    }
}