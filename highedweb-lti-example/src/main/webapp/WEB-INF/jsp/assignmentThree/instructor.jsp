<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<table>
	<tr>
		<td>First Name</td>
		<td>Last Name</td>
		<td>Submission Title</td>
	</tr>
	
	<c:forEach var="row" items="${assignmentList}">
	<tr>
		<td><c:out value="${row.lisPersonNameGiven}" /></td>
		<td><c:out value="${row.lisPersonNameFamily}" /></td>
		<td><c:out value="${row.assignmentTitle}" /></td>
		<td>
			<form:form method="post" modelAttribute="assignmentOutcome" action="${pageContext.request.contextPath}/assignmentThreeInstructorPost.htm"> 
				<form:input type="hidden" path="outcomeId" value="${row.id}" />
				<form:input type="text" path="outcomeGrade" value="${row.assignmentGrade}" />
				<input type="submit" name="Submit" value="Submit" />
			</form:form>
		</td>
	</tr>
	</c:forEach>
	
</table>

</body>
</html>