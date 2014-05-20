highedweb-lti-example
=====================

This is example code that will accompany a presentation at the Michigan Higher Ed Web Conference 2014.

The goal of this applicaiton is to build an LTI Application that can be plugged into any LMS system.  

Requirements:
Java JDK - Anything greater than Java 6 should be adequate.
Servlet Container - Tomcat 6.0.37 was used in development
Database - This uses HSQL for ease of deployment but I would recommend something else, i.e. MySQL 
Maven
LMS - This was primarily tested with Instructure Canvas

Getting Started
Setup your local database.  MySQL would be most convient, but you should be able to choose any.  If you do not use MySQL you will likely need to add the depency into the pom.xml and update the database configuration there.
Update the pom.xml.  This should include a pointer to your Tomcat installation, or deployment location.  Also, you can update the database connection to fit your needs.
Update the highedweb-lti-service.xml file.  This file contain the key/secret for the integration.  You can update this match your needs.
Build and deploy the applcation
  mvn install
Configure the LTI in your LMS.  This will vary based on LMS.
