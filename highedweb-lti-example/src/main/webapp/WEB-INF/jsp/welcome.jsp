<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assignments</title>
</head>
<body>

<h1>Overview</h1>
<p>
This is example code that will accompany a presentation at the Michigan Higher Ed Web Conference 2014.  The goal of this applicaiton is to build an LTI Application that can be plugged into any LMS system.
</p>
<h2>Assignment One</h2>
<a href="${pageContext.request.contextPath}/assignmentOne.htm">${pageContext.request.contextPath}/assignmentOne.htm</a>
<ul>
	<li>Launch Tool</li>
	<li>Validates OAuth Signature</li>
</ul>
<h2>Assignment Two</h2>
<a href="${pageContext.request.contextPath}/assignmentTwo.htm">${pageContext.request.contextPath}/assignmentTwo.htm</a>
	<ul>
		<li>Identify User Type</li>
		<li>Student View</li>
		<li>Instructor View</li>
	</ul>
<h2>Assignment Three</h2>
<a href="${pageContext.request.contextPath}/assignmentThree.htm">${pageContext.request.contextPath}/assignmentThree.htm</a>
<ul>
	<li>Data Persistence</li>
	<li>Instructor can provide an outcome</li>
</ul>	

</body>
</html>