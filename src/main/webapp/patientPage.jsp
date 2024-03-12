<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Details</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Patient Details</h2>

    <%
        Map<String, String> patientDetails = (Map<String, String>) request.getAttribute("patientDetails");
        if (patientDetails != null && !patientDetails.isEmpty()) {
    %>
    <table>
        <tr> <th>ID:</th> <td><%= patientDetails.get("ID") %></td> </tr>
        <tr> <th>Birth Date:</th> <td><%= patientDetails.get("BIRTHDATE") %></td> </tr>
        <tr> <th>Death Date:</th> <td><%= patientDetails.get("DEATHDATE") %></td>  </tr>
        <tr> <th>SSN:</th> <td><%= patientDetails.get("SSN") %></td> </tr>
        <tr> <th>Drivers:</th> <td><%= patientDetails.get("DRIVERS") %></td> </tr>
        <tr> <th>Passport:</th> <td><%= patientDetails.get("PASSPORT") %></td> </tr>
        <tr> <th>Prefix:</th> <td><%= patientDetails.get("PREFIX") %></td> </tr>
        <tr> <th>First Name:</th> <td><%= patientDetails.get("FIRST") %></td> </tr>
        <tr> <th>Last Name:</th> <td><%= patientDetails.get("LAST") %></td> </tr>
        <tr> <th>Suffix:</th> <td><%= patientDetails.get("SUFFIX") %></td> </tr>
        <tr> <th>Maiden:</th> <td><%= patientDetails.get("MAIDEN") %></td> </tr>
        <tr> <th>Marital:</th> <td><%= patientDetails.get("MARITAL") %></td> </tr>
        <tr> <th>Race:</th> <td><%= patientDetails.get("RACE") %></td> </tr>
        <tr> <th>Ethnicity:</th> <td><%= patientDetails.get("ETHNICITY") %></td> </tr>
        <tr> <th>Gender:</th> <td><%= patientDetails.get("GENDER") %></td> </tr>
        <tr> <th>Birthplace:</th> <td><%= patientDetails.get("BIRTHPLACE") %></td> </tr>
        <tr> <th>Address:</th> <td><%= patientDetails.get("ADDRESS") %></td> </tr>
        <tr> <th>City:</th> <td><%= patientDetails.get("CITY") %></td> </tr>
        <tr> <th>State:</th> <td><%= patientDetails.get("STATE") %></td> </tr>
        <tr> <th>Zip:</th> <td><%= patientDetails.get("ZIP") %></td> </tr>
    </table>
    <% } else { %>
    <p>Patient not found.</p>
    <% } %>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
