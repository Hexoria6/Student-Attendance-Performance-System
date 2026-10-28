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

@WebServlet("/MarksServlet")
public class MarksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Student Marks</title></head>");
        out.println("<body style='font-family:Arial;margin:30px'>");
        out.println("<h2>Enter Student Marks</h2>");
        out.println("<form method='post' action='MarksServlet'>");
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
        out.println("Subject: <input type='text' name='subject_name' required><br><br>");
        out.println("Maximum Marks: <input type='number' name='max_marks' value='100' min='1' required><br><br>");
        out.println("Marks Obtained: <input type='number' name='marks_obtained' min='0' step='0.01' required><br><br>");
        out.println("<button type='submit'>Save Marks</button>");
        out.println("</form></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            int studentId = Integer.parseInt(request.getParameter("student_id"));
            String subject = request.getParameter("subject_name").trim();
            int maxMarks = Integer.parseInt(request.getParameter("max_marks"));
            double obtained = Double.parseDouble(request.getParameter("marks_obtained"));

            if (subject.isEmpty() || maxMarks <= 0 ||
                    obtained < 0 || obtained > maxMarks) {
                out.println("<h2>Invalid marks. Please check your entries.</h2>");
                out.println("<a href='MarksServlet'>Go Back</a>");
                return;
            }

            String sql = "INSERT INTO Marks "
                    + "(student_id, subject_name, max_marks, marks_obtained) "
                    + "VALUES (?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, studentId);
                ps.setString(2, subject);
                ps.setInt(3, maxMarks);
                ps.setDouble(4, obtained);
                ps.executeUpdate();
            }

            out.println("<h2>Marks saved successfully!</h2>");
            out.println("<a href='MarksServlet'>Enter More Marks</a>");

        } catch (NumberFormatException e) {
            out.println("<h2>Please enter valid numeric values.</h2>");
            out.println("<a href='MarksServlet'>Go Back</a>");
        } catch (Exception e) {
            out.println("<h2>Unable to save marks.</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<a href='MarksServlet'>Go Back</a>");
        }
    }
}