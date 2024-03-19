<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <style>
        .content {
            display: flex;
            justify-content: flex-start;
        }

        .patient-list {
            margin-right: 100px;
        }

        .chart-container {
            width: 700px
        }

    </style>

</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Patients:</h2>
    <form method="get" action="patientList.html">
        <label for="sort-by">Sort by:</label>
        <select id="sort-by" name="sort-by">
            <option value="default" <% if (request.getParameter("sort-by") == null || request.getParameter("sort-by").equals("default")) { %>
                    selected <% } %>>Default
            </option>
            <option value="first-name" <% if (request.getParameter("sort-by") != null && request.getParameter("sort-by").equals("first-name")) { %>
                    selected <% } %>>First Name
            </option>
            <option value="last-name" <% if (request.getParameter("sort-by") != null && request.getParameter("sort-by").equals("last-name")) { %>
                    selected <% } %>>Last Name
            </option>
            <option value="age" <% if (request.getParameter("sort-by") != null && request.getParameter("sort-by").equals("age")) { %>
                    selected <% } %>>Age
            </option>
        </select>
        <label for="reverse-order">Reverse Order:</label>
        <input type="checkbox" id="reverse-order" name="reverse-order"
               value="false" <% if (request.getParameter("reverse-order") != null) { %> checked <% } %>>

        <label for="group-by">Group by:</label>
        <select id="group-by" name="group-by">
            <option value="none" <% if (request.getParameter("group-by") == null || request.getParameter("group-by").equals("none")) { %>
                    selected <% } %>>None
            </option>
            <option value="gender" <% if (request.getParameter("group-by") != null && request.getParameter("group-by").equals("gender")) { %>
                    selected <% } %>>Gender
            </option>
            <option value="city" <% if (request.getParameter("group-by") != null && request.getParameter("group-by").equals("city")) { %>
                    selected <% } %>>City
            </option>
            <option value="state" <% if (request.getParameter("group-by") != null && request.getParameter("group-by").equals("state")) { %>
                    selected <% } %>>State
            </option>
            <option value="birthplace" <% if (request.getParameter("group-by") != null && request.getParameter("group-by").equals("birthplace")) { %>
                    selected <% } %>>Birthplace
            </option>
            <option value="race" <% if (request.getParameter("group-by") != null && request.getParameter("group-by").equals("race")) { %>
                    selected <% } %>>Race
            </option>
            <option value="ethnicity" <% if (request.getParameter("group-by") != null && request.getParameter("group-by").equals("ethnicity")) { %>
                    selected <% } %>>Ethnicity
            </option>
        </select>
        <button type="submit" name="apply">Apply</button>
    </form>

    <div class="content">

        <div class="patient-list">
            <%
                Map<String, List<String>> patientData = (Map<String, List<String>>) request.getAttribute("patientNames");
            %>

            <ul>
                <% if (patientData.size() == 1 && patientData.containsKey("")) { %>
                <%
                    // no grouping
                    List<String> patientNames = patientData.get("");
                    for (String patientName : patientNames) {
                        String encodedPatientName = URLEncoder.encode(patientName, StandardCharsets.UTF_8);
                        String href = "patientPage.html?patient=" + encodedPatientName;
                %>
                <li><a href="<%=href%>"><%=patientName%>
                </a></li>
                <% }
                %>

                <% } else { %>
                <%
                    // grouping logic
                    for (Map.Entry<String, List<String>> group : patientData.entrySet()) {
                        int groupCount = group.getValue().size();
                %>
                <h3><%= group.getKey() %> (<%= groupCount %>)</h3>
                <ul>
                    <% for (String patientName : group.getValue()) {
                        String encodedPatientName = URLEncoder.encode(patientName, StandardCharsets.UTF_8);
                        String href = "patientPage.html?patient=" + encodedPatientName;
                    %>
                    <li><a href="<%=href%>"><%=patientName%>
                    </a></li>
                    <% } %>
                </ul>
                <% }
                %>
                <% } %>
            </ul>
        </div>


        <div class="chart-container">
            <canvas id="chart"></canvas>
            <script>
                let labels = [];
                let counts = [];
                <%
                    for (Map.Entry<String, List<String>> group : patientData.entrySet()) {
                        int groupCount = group.getValue().size();
                        String fixedKey = group.getKey().replace("'", "\\'");
                %>
                if ("<%=fixedKey%>" !== "") {
                    labels.push('<%=fixedKey%>');
                    counts.push(<%=groupCount%>);
                }
                <% } %>

                let ctx = document.getElementById('chart').getContext('2d');
                let pieChart = new Chart(ctx, {
                    type: 'doughnut',
                    data: {
                        labels: labels,
                        datasets: [{
                            data: counts,
                        }]
                    },
                    options: {
                        responsive: true,
                    }
                });
            </script>
        </div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>
</body>
</html>
