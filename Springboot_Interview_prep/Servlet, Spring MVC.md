###### **🧩 What is a Servlet?**

Servlet is a Java class that runs on a server and handles client requests and sends responses.

When a browser sends a request → Servlet processes it → sends response back.



Client → Tomcat Server → Servlet → Database → Servlet → Tomcat → Client



The Servlet Container manages:

* Servlet lifecycle
* Threading
* Request \& Response objects
* Security



###### **🧭 What is Apache Tomcat?**

Tomcat is a web server and servlet container that runs Java web applications.

It provides:

* Servlet engine
* JSP engine
* HTTP server





🔹 Why Tomcat is needed?

Servlet cannot run alone.

It needs a container to:

* create objects
* manage lifecycle
* handle requests
* manage threads



That container is Tomcat. Springboot uses Embedded Tomcat.





###### **🧠 How Spring DispatcherServlet Works?**

DispatcherServlet is the front controller of Spring MVC.

When request received by tomcat Dispatcher servlet asks to HandlerMapping -

"Which controller should handle this request?"



Flow of MVC:-



Client

  ↓

Tomcat

  ↓

DispatcherServlet   ← Front Controller

  ↓

HandlerMapping     ← finds controller

  ↓

Controller

  ↓

Service

  ↓

Repository / DB

  ↑

Controller returns data

  ↑

ViewResolver / HttpMessageConverter

  ↑

DispatcherServlet

  ↑

Tomcat

  ↑

Client





###### **🌐 What is Spring MVC?**

Spring MVC is a framework to build web applications using the Model–View–Controller design pattern.

🧱 MVC = Model + View + Controller

| Layer      | Role                            |

| ---------- | ------------------------------- |

| Model      | Business data (Entities, DTOs)  |

| View       | UI (HTML, JSP, Thymeleaf, JSON) |

| Controller | Handles request \& response      |



Q2: Difference between Spring MVC and Spring Boot?



| Spring MVC   | Spring Boot                    |

| ------------ | ------------------------------ |

| Framework    | Framework + Auto-configuration |

| Manual setup | Embedded server, no XML        |

| Need server  | Embedded Tomcat                |





Q3: How does Spring MVC convert object to JSON?

Using HttpMessageConverter (Jackson).



###### **🧪 30-Second Explanation (Perfect Interview Answer)**

Spring MVC is a web framework based on the MVC pattern. It uses DispatcherServlet as the front controller. DispatcherServlet receives all requests, uses HandlerMapping to locate the appropriate controller, executes it via HandlerAdapter, and then uses view resolvers or message converters to generate the response back to the client.

