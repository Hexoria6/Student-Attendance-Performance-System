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

@WebServlet("/ViewStudentsServlet")
public class ViewStudentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>All Students</title>");
        out.println("<style>");
        out.println("body{font-family:Arial;background:#f2f4f8;padding:25px;}");
        out.println("table{border-collapse:collapse;width:100%;background:white;}");
        out.println("th,td{border:1px solid #ddd;padding:12px;text-align:left;}");
        out.println("th{background:#2563eb;color:white;}");
        out.println("</style></head><body>");
        out.println("<h2>Registered Students</h2>");
        out.println("<table><tr><th>ID</th><th>Roll No</th>");
        out.println("<th>Name</th><th>Email</th><th>Course</th>");
        out.println("<th>Semester</th></tr>");

        String sql = "SELECT * FROM Students ORDER BY student_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("student_id") + "</td>");
                out.println("<td>" + rs.getString("roll_no") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("course") + "</td>");
                out.println("<td>" + rs.getInt("semester") + "</td>");
                out.println("</tr>");
            }

        } catch (Exception e) {
            out.println("<tr><td colspan='6'>Unable to load students.</td></tr>");
            e.printStackTrace();
        }

        out.println("</table>");
        out.println("<p><a href='register.jsp'>Register Another Student</a></p>");
        out.println("</body></html>");
    }
}