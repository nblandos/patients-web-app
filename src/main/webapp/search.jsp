<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Search</h1>
    <form method="post" action="${pageContext.request.contextPath}/runsearch.html">
        <label>
            <input type="text" name="search-string" placeholder="Enter search keyword here"/>
        </label>
        <input type="submit" value="Search"/>

        <label for="search-type">Search by:</label>
        <select name="search-type" id="search-type">
            <option value="name">Name</option>
            <option value="id">ID</option>
            <option value="ssn">SSN</option>
            <option value="drivers">Drivers</option>
            <option value="race-ethnicity">Race/Ethnicity</option>
            <option value="birthplace">Birthplace</option>
            <option value="location">Location</option>
        </select>
    </form>

</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>