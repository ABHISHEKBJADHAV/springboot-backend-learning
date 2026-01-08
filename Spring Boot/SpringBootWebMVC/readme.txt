Your .jsp pages should only be in folder webApp.
Note that path of folder webApp and name of folder i.e. webApp should be exact. Because
Spring Boot’s embedded Tomcat only serves JSPs from src/main/webapp.
If you want to move to another folder inside webApp then you can use application.properties
e.g.
Suppose your jsp files are in webApp/views then use
spring.mvc.view.prefix=/views/
spring.mvc.view.suffix=.jsp

Model from MVC. Model is just an interface to transfer data between view (JSP/Html) and Java
