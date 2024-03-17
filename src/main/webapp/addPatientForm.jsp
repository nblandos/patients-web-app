<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Patient</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div>
    <h1>Add Patient</h1>
    <form action="AddPatientServlet" method="post">
        <label for="id">ID:</label><br>
        <input type="text" id="id" name="id"><br>
        <label for="birthdate">Birth Date:</label><br>
        <input type="text" id="birthdate" name="birthdate"><br>
        <label for="deathdate">Death Date:</label><br>
        <input type="text" id="deathdate" name="deathdate"><br>
        <label for="ssn">SSN:</label><br>
        <input type="text" id="ssn" name="ssn"><br>
        <label for="drivers">Drivers:</label><br>
        <input type="text" id="drivers" name="drivers"><br>
        <label for="passport">Passport:</label><br>
        <input type="text" id="passport" name="passport"><br>
        <label for="prefix">Prefix:</label><br>
        <input type="text" id="prefix" name="prefix"><br>
        <label for="first">First Name:</label><br>
        <input type="text" id="first" name="first"><br>
        <label for="last">Last Name:</label><br>
        <input type="text" id="last" name="last"><br>
        <label for="suffix">Suffix:</label><br>
        <input type="text" id="suffix" name="suffix"><br>
        <label for="maiden">Maiden:</label><br>
        <input type="text" id="maiden" name="maiden"><br>
        <label for="marital">Marital:</label><br>
        <input type="text" id="marital" name="marital"><br>
        <label for="race">Race:</label><br>
        <input type="text" id="race" name="race"><br>
        <label for="ethnicity">Ethnicity:</label><br>
        <input type="text" id="ethnicity" name="ethnicity"><br>
        <label for="gender">Gender:</label><br>
        <input type="text" id="gender" name="gender"><br>
        <label for="birthplace">Birthplace:</label><br>
        <input type="text" id="birthplace" name="birthplace"><br>
        <label for="address">Address:</label><br>
        <input type="text" id="address" name="address"><br>
        <label for="city">City:</label><br>
        <input type="text" id="city" name="city"><br>
        <label for="state">State:</label><br>
        <input type="text" id="state" name="state"><br>
        <label for="zip">Zip:</label><br>
        <input type="text" id="zip" name="zip"><br>
        <input type="submit" value="Submit">
    </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>

</html>