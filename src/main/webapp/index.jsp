<!DOCTYPE html>
<html>
<head>
    <title>Student Management System</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f1f5f9;
            color: #1e293b;
        }

        header {
            background: #1e3a8a;
            color: white;
            padding: 25px;
            text-align: center;
        }

        .container {
            max-width: 900px;
            margin: 35px auto;
            padding: 20px;
        }

        .cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 20px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 3px 10px #00000015;
        }

        .card h3 {
            color: #1d4ed8;
        }

        .card p {
            color: #64748b;
        }

        .card a {
            display: inline-block;
            background: #2563eb;
            color: white;
            padding: 10px 14px;
            border-radius: 5px;
            text-decoration: none;
        }

        footer {
            text-align: center;
            padding: 20px;
            color: #64748b;
        }
    </style>
</head>
<body>

<header>
    <h1>Student Management System</h1>
    <p>Attendance and Academic Performance Management</p>
</header>

<div class="container">
    <h2>Dashboard</h2>
    <p>Select a module to continue.</p>

    <div class="cards">

        <div class="card">
            <h3>Student Registration</h3>
            <p>Add new student details to the database.</p>
            <a href="register.jsp">Register Student</a>
        </div>

        <div class="card">
            <h3>View Students</h3>
            <p>View all registered students.</p>
            <a href="ViewStudentsServlet">View Students</a>
        </div>

        <div class="card">
            <h3>Attendance</h3>
            <p>Mark daily student attendance.</p>
            <a href="AttendanceServlet">Manage Attendance</a>
        </div>

        <div class="card">
            <h3>Marks Management</h3>
            <p>Enter subject-wise student marks.</p>
            <a href="MarksServlet">Manage Marks</a>
        </div>

        <div class="card">
            <h3>Performance Report</h3>
            <p>View marks, percentages, and grades.</p>
            <a href="PerformanceServlet">View Report</a>
        </div>

    </div>
</div>

<footer>
    Student Attendance and Performance Management System
</footer>

</body>
</html>