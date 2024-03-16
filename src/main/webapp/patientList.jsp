<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Patients:</h2>
  <form method="get" action="patientList.html">
    <label for="sort-by">Sort by:</label>
    <select id="sort-by" name="sort-by">
      <option value="default" <% if (request.getParameter("sort-by") == null || request.getParameter("sort-by").equals("default")) { %> selected <% } %>>Default</option>
      <option value="first-name" <% if (request.getParameter("sort-by") != null && request.getParameter("sort-by").equals("first-name")) { %> selected <% } %>>First Name</option>
      <option value="last-name" <% if (request.getParameter("sort-by") != null && request.getParameter("sort-by").equals("last-name")) { %> selected <% } %>>Last Name</option>
      <option value="age" <% if (request.getParameter("sort-by") != null && request.getParameter("sort-by").equals("age")) { %> selected <% } %>>Age</option>
    </select>
    <label for="reverse-order">Reverse Order:</label>
    <input type="checkbox" id="reverse-order" name="reverse-order" value="false" <% if (request.getParameter("reverse-order") != null) { %> checked <% } %> >
    <button type="submit">Sort</button>
  </form>

  <ul>
    <%
      List<String> patientNames = (List<String>) request.getAttribute("patientNames");
      for (String patientName : patientNames)
      {
        String encodedPatientName = URLEncoder.encode(patientName, "UTF-8");
        String href = "patientPage.html?patient=" + encodedPatientName;
    %>
    <li><a href="<%=href%>"><%=patientName%></a>
    </li>
    <% } %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
