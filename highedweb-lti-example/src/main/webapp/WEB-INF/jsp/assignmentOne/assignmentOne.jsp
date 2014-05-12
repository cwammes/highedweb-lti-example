<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assignments</title>
</head>
<body>


<h1>Submissions</h1>

<table style="border: 1px solid">
<tr>
	<th>Parameter Name</th>
	<th>Parameter Value</th>	
</tr>

<c:forEach var="ltiParam" items="${ltiParams}">
<tr>
	<td>${ltiParam.paramName}</td>
	<td><c:out value="${ltiParam.paramValue}" /></td>
</tr>
</c:forEach>
</table>


</body>
</html>