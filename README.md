# REST API's

Developed REST API's where users can create profiles and post messages and comment for that message

Each request has to be made with "http://localhost:8080/Messenger/webapi/* " prefix.

When you deploy this project on any application server (Tomcat, Gassfish) it'll be accessible in the server url localhost 8080/ApplicationContext

This application is configured to accept REST API requests at "/webapi" URL. This is the only way to access the REST API of this project

A servlet is configured in web.xml file called "Jersey Web Application". The servlet class comes bundled with the Jersey Jars. This servlet is mapped with the "/webapi/*" URL pattern.

So any request that comes to this application that has "/webapi" as the prefix, this servlet handles that URL

The overall concept is just mapping jersey servlet to "/webapi/*"
