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

    <%
        Map<String, String> patientDetails = (Map<String, String>) request.getAttribute("patientDetails");
        if (patientDetails != null && !patientDetails.isEmpty()) {
            String patientId = patientDetails.get("ID");
            String patientName = patientDetails.get("FIRST") + " " + patientDetails.get("LAST");
    %>
    <h2><%= patientName %></h2>
    <table>
        <%--displays N/A instead of null if column name does not exist--%>
        <tr>
            <th>ID:</th>
            <td><%= patientDetails.get("ID") != null ? patientDetails.get("ID") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Birth Date:</th>
            <td><%= patientDetails.get("BIRTHDATE") != null ? patientDetails.get("BIRTHDATE") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Death Date:</th>
            <td><%= patientDetails.get("DEATHDATE") != null ? patientDetails.get("DEATHDATE") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>SSN:</th>
            <td><%= patientDetails.get("SSN") != null ? patientDetails.get("SSN") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Drivers:</th>
            <td><%= patientDetails.get("DRIVERS") != null ? patientDetails.get("DRIVERS") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Passport:</th>
            <td><%= patientDetails.get("PASSPORT") != null ? patientDetails.get("PASSPORT") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Prefix:</th>
            <td><%= patientDetails.get("PREFIX") != null ? patientDetails.get("PREFIX") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Suffix:</th>
            <td><%= patientDetails.get("SUFFIX") != null ? patientDetails.get("SUFFIX") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Maiden:</th>
            <td><%= patientDetails.get("MAIDEN") != null ? patientDetails.get("MAIDEN") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Marital:</th>
            <td><%= patientDetails.get("MARITAL") != null ? patientDetails.get("MARITAL") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Race:</th>
            <td><%= patientDetails.get("RACE") != null ? patientDetails.get("RACE") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Ethnicity:</th>
            <td><%= patientDetails.get("ETHNICITY") != null ? patientDetails.get("ETHNICITY") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Gender:</th>
            <td><%= patientDetails.get("GENDER") != null ? patientDetails.get("GENDER") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Birthplace:</th>
            <td><%= patientDetails.get("BIRTHPLACE") != null ? patientDetails.get("BIRTHPLACE") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Address:</th>
            <td><%= patientDetails.get("ADDRESS") != null ? patientDetails.get("ADDRESS") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>City:</th>
            <td><%= patientDetails.get("CITY") != null ? patientDetails.get("CITY") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>State:</th>
            <td><%= patientDetails.get("STATE") != null ? patientDetails.get("STATE") : "N/A" %>
            </td>
        </tr>
        <tr>
            <th>Zip:</th>
            <td><%= patientDetails.get("ZIP") != null ? patientDetails.get("ZIP") : "N/A" %>
            </td>
        </tr>
    </table>

    <form action="deletePatient.html" method="post">
        <input type="hidden" name="patientId" value="<%= patientId %>">
        <input type="submit" value="Delete Patient" onclick="return confirm('Are you sure you want to delete <%= patientName %>?')">
    </form>

    <form action="editPatientForm.html" method="get">
        <input type="hidden" name="patientId" value="<%= patientId %>">
        <input type="submit" value="Edit Patient">
    </form>


    <% } else { %>
    <p>Patient not found.</p>
    <% } %>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
