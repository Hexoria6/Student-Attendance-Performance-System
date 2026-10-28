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

@WebServlet("/PerformanceServlet")
public class PerformanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Performance Report</title>");
        out.println("<style>");
        out.println("body{font-family:Arial;margin:30px;background:#f2f4f8;}");
        out.println("table{width:100%;border-collapse:collapse;background:white;}");
        out.println("th,td{padding:12px;border:1px solid #ddd;text-align:left;}");
        out.println("th{background:#2563eb;color:white;}");
        out.println("</style></head><body>");
        out.println("<h2>Student Performance Report</h2>");
        out.println("<table><tr><th>Roll No</th><th>Name</th>");
        out.println("<th>Subject</th><th>Marks Obtained</th>");
        out.println("<th>Maximum Marks</th><th>Percentage</th>");
        out.println("<th>Grade</th></tr>");

        String sql =
            "SELECT s.roll_no, s.name, m.subject_name, " +
            "m.marks_obtained, m.max_marks, " +
            "(m.marks_obtained / m.max_marks) * 100 AS percentage " +
            "FROM Students s JOIN Marks m ON s.student_id = m.student_id " +
            "ORDER BY s.roll_no, m.subject_name";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                double percentage = rs.getDouble("percentage");
                String grade;

                if (percentage >= 90) {
                    grade = "A+";
                } else if (percentage >= 80) {
                    grade = "A";
                } else if (percentage >= 70) {
                    grade = "B";
                } else if (percentage >= 60) {
                    grade = "C";
                } else if (percentage >= 40) {
                    grade = "D";
                } else {
                    grade = "F";
                }

                out.println("<tr>");
                out.println("<td>" + rs.getString("roll_no") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("subject_name") + "</td>");
                out.println("<td>" + rs.getDouble("marks_obtained") + "</td>");
                out.println("<td>" + rs.getInt("max_marks") + "</td>");
                out.printf(java.util.Locale.US,
                    "<td>%.2f%%</td>%n", percentage);
                out.println("<td>" + grade + "</td>");
                out.println("</tr>");
            }

        } catch (Exception e) {
            out.println("<tr><td colspan='7'>Unable to load performance report.</td></tr>");
            e.printStackTrace();
        }

        out.println("</table>");
        out.println("<p><a href='MarksServlet'>Enter More Marks</a></p>");
        out.println("</body></html>");
    }
}