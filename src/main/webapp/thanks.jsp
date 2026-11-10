<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
<img src="styles/img/logo.png" alt="Murach Logo" id="logo">

<h1>Thanks for filling out our survey</h1>

<p>Here is the information that you entered:</p>

<!-- Thông tin cá nhân từ User bean -->
<p><label>First Name:</label><span>${user.firstName}</span></p>
<p><label>Last Name:</label><span>${user.lastName}</span></p>
<p><label>Email:</label><span>${user.email}</span></p>
<p><label>Date of Birth:</label><span>${user.dateOfBirth}</span></p>

<!-- Thông tin khảo sát từ Survey bean -->
<h2>How did you hear about us?</h2>
<p><label>Heard From:</label><span>${survey.hearAbout}</span></p>

<h2>Announcements:</h2>
<p><label>Updates:</label><span>${survey.wantUpdate}</span></p>
<p><label class="wide-label">Announcements:</label><span>${survey.wantAnnouncement}</span></p>

<h2>Contact preferences:</h2>
<p><label class="wide-label">Contact via:</label><span>${survey.contact}</span></p>

<form action="index.html" method="get">
    <input type="submit" value="Return" id="submit">
</form>
</body>
</html>