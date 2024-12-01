<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Search Result</h1>
    <%
        List<String> patients = (List<String>) request.getAttribute("result");
        if (!patients.isEmpty()) {
    %>
    <ul>
        <%
            for (String patient : patients) {
                String encodedPatientName = URLEncoder.encode(patient, StandardCharsets.UTF_8);
                String href = "patientPage.html?patient=" + encodedPatientName;
        %>
        <li><a href="<%=href%>"><%=patient%>
        </a>
                <% }
    } else
    {%>
            <p>Nothing found</p>
                <%}%>
    </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>