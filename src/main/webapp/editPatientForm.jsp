<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Patient</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div>
    <h1>Edit Patient</h1>
    <form action="editPatient.html" method="post">
        <input type="hidden" id="id" name="id" value="${ID}">
        <label for="birthdate">Birth Date:</label><br>
        <input type="text" id="birthdate" name="birthdate" value="${BIRTHDATE}"><br>
        <label for="deathdate">Death Date:</label><br>
        <input type="text" id="deathdate" name="deathdate" value="${DEATHDATE}"><br>
        <label for="ssn">SSN:</label><br>
        <input type="text" id="ssn" name="ssn" value="${SSN}"><br>
        <label for="drivers">Drivers:</label><br>
        <input type="text" id="drivers" name="drivers" value="${DRIVERS}"><br>
        <label for="passport">Passport:</label><br>
        <input type="text" id="passport" name="passport" value="${PASSPORT}"><br>
        <label for="prefix">Prefix:</label><br>
        <input type="text" id="prefix" name="prefix" value="${PREFIX}"><br>
        <label for="first">First Name:</label><br>
        <input type="text" id="first" name="first" value="${FIRST}"><br>
        <label for="last">Last Name:</label><br>
        <input type="text" id="last" name="last" value="${LAST}"><br>
        <label for="suffix">Suffix:</label><br>
        <input type="text" id="suffix" name="suffix" value="${SUFFIX}"><br>
        <label for="maiden">Maiden:</label><br>
        <input type="text" id="maiden" name="maiden" value="${MAIDEN}"><br>
        <label for="marital">Marital:</label><br>
        <input type="text" id="marital" name="marital" value="${MARITAL}"><br>
        <label for="race">Race:</label><br>
        <input type="text" id="race" name="race" value="${RACE}"><br>
        <label for="ethnicity">Ethnicity:</label><br>
        <input type="text" id="ethnicity" name="ethnicity" value="${ETHNICITY}"><br>
        <label for="gender">Gender:</label><br>
        <input type="text" id="gender" name="gender" value="${GENDER}"><br>
        <label for="birthplace">Birthplace:</label><br>
        <input type="text" id="birthplace" name="birthplace" value="${BIRTHPLACE}"><br>
        <label for="address">Address:</label><br>
        <input type="text" id="address" name="address" value="${ADDRESS}"><br>
        <label for="city">City:</label><br>
        <input type="text" id="city" name="city" value="${CITY}"><br>
        <label for="state">State:</label><br>
        <input type="text" id="state" name="state" value="${STATE}"><br>
        <label for="zip">Zip:</label><br>
        <input type="text" id="zip" name="zip" value="${ZIP}"><br>
        <input type="submit" value="Submit">
    </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>