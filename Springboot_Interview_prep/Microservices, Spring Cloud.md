##### **🧩 MICROSERVICES**



🧠 What is Microservices?

Microservices architecture is a design style where an application is built as a collection of small, independent, loosely coupled services that communicate over the network.

Each service:

* Has its own codebase
* Has its own database (ideally)
* Can be deployed independently





🧱 Why not Monolith?

Monolith problems:

* One big codebase
* One deployment unit
* One failure affects whole system
* Hard to scale



Microservices solve:

* Independent deployment
* Independent scaling
* Technology flexibility
* Faster development





🏗 Typical Microservices Architecture (like your Quiz project):



Client (Angular)

     ↓

API Gateway

     ↓

Service Registry (Eureka)

     ↓

 ┌───────────┬────────────┬─────────────┐

 | Quiz Svc      | Question   	  | Auth Svc        |

 | (Spring)  	 | Service    	  | (JWT)           |

 └───────────┴────────────┴─────────────┘

     ↓              ↓             ↓

  Quiz DB     Question DB     User DB





Scaling becomes easy:

Quiz Service: 3 containers

Auth Service: 2 containers



###### **⚙ Key Components (3 YOE Must Know)**

1️⃣ API Gateway

Single entry point.

Handles:

* Routing
* Auth
* Rate limiting
* Logging



2️⃣ Service Registry (Eureka)

Services register themselves.

Other services discover them dynamically.



3️⃣ Inter-Service Communication

* REST (Feign client)
* Async (Kafka / RabbitMQ)



4️⃣ Config Server

Centralized configuration.



5️⃣ Load Balancing

Handled by Spring Cloud + Gateway + Cloud infra.





###### **🎯 Common Interview Questions**



Q1: Why Microservices?

Better scalability, faster deployments, team autonomy, fault isolation.



Q2: How do services communicate?

REST using Feign clients or asynchronous messaging.



Q3: What is Service Discovery?

A mechanism to dynamically locate services using Eureka or similar registry.



Q4: How do you handle failure?

Circuit breakers, retries, timeouts, fallback logic.



Q5: How do you secure microservices?

JWT, OAuth2, API Gateway authentication, service-to-service auth.



Q6: How do you deploy microservices?

Dockerize each service, push images to registry, deploy on orchestration platform like AWS ECS.





###### **🧠 Situation**



API Gateway is running on older Spring version

Business services (Quiz, Auth, Question) are running on Spring Boot 3 / Spring 6 (or Spring 4 in your example)



api-gateway-image  → contains Spring X + Java Y

quiz-service-image → contains Spring 4 + Java 17

auth-service-image → contains Spring 4 + Java 17







#### **Spring Cloud:**



What is Spring Cloud?

Spring Cloud is a set of tools built on top of Spring Boot to develop microservices-based systems.



When you have many microservices, you face problems like:

* How will services find each other?
* What if one service is down?
* How do we handle load balancing?
* How do we centralize configuration?
* How do we protect system from failures?

Spring Cloud solves all of these.



🧠 Core Components of Spring Cloud

| Feature                        | Purpose                       |

| ------------------------------ | ----------------------------- |

| Eureka                         | Service Discovery             |

| Config Server                  | Central configuration         |

| API Gateway                    | Single entry point            |

| Load Balancer                  | Distribute traffic            |

| Circuit Breaker (Resilience4j) | Prevent cascading failures    |

| Feign Client                   | Easy service-to-service calls |



1️⃣ Service Discovery – Eureka

Instead of hardcoding service URLs:

* All services register themselves with Eureka.
* When one service wants another, it asks Eureka.



2️⃣ API Gateway

Instead of calling services directly: Client → API Gateway → Internal Services

Benefits:

* Authentication \& security at one place
* Logging
* Rate limiting
* Routing



3️⃣ Central Configuration – Config Server

Instead of having separate: quiz-service application.yml, question-service application.yml, auth-service application.yml

You store all configs in one Git repository. Config Server serves them dynamically.



4️⃣ Load Balancing

When you have multiple instances of same service Spring Cloud LoadBalancer distributes traffic automatically.



5️⃣ Circuit Breaker – Resilience4j

Circuit Breaker is a design pattern that prevents your system from repeatedly calling a failing service and protects your application from cascading failures.

Suppose: quiz-service → calls → question-service

If question-service goes down:

quiz-service keeps trying → each request hangs → thread pool fills up → quiz-service also crashes → whole system becomes unavailable





6️⃣ Feign Client

Instead of writing RestTemplate / WebClient code:



@FeignClient(name = "question-service")

public interface QuestionClient {



&nbsp;   @GetMapping("/questions/{id}")

&nbsp;   Question getQuestion(@PathVariable int id);

}



No RestTemplate. No WebClient. No URL handling.



How Feign Works Internally?

When you write: @FeignClient(name = "question-service")

Spring does this automatically:

1. Finds question-service from Eureka
2. Picks one healthy instance (load balanced)
3. Makes HTTP call
4. Converts JSON to Java object
5. Returns result to your method
