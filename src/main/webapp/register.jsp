<!DOCTYPE html>
<html>
<head>
    <title>Student Registration</title>
    <style>
        body {
            font-family: Arial;
            background: #f2f4f8;
            text-align: center;
        }

        .container {
            background: white;
            width: 350px;
            margin: 50px auto;
            padding: 25px;
            border-radius: 10px;
        }

        input {
            width: 90%;
            padding: 10px;
            margin: 8px;
        }

        button {
            padding: 10px 20px;
            background: #2563eb;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Student Registration</h2>

    <form action="StudentServlet" method="post">
        <input type="text" name="roll_no"
               placeholder="Roll Number" required>

        <input type="text" name="name"
               placeholder="Student Name" required>

        <input type="email" name="email"
               placeholder="Email">

        <input type="text" name="course"
               placeholder="Course" required>

        <input type="number" name="semester"
               placeholder="Semester" min="1" required>

        <button type="submit">Register Student</button>
    </form>
</div>

</body>
</html>