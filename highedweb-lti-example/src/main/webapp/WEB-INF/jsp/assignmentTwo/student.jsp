<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Upload an assignment using this form

<form:form method="post" modelAttribute="assignmentTwoSubmission" action="${pageContext.request.contextPath}/assignmentTwoPost.htm"> 

	<form:label path="title">Title</form:label><form:input type="text" path="title" /><br />
	<form:label path="body">Body</form:label><form:input type="textarea" path="body" />

	<input type="submit" value="Submit"/>
</form:form>


</body>
</html>