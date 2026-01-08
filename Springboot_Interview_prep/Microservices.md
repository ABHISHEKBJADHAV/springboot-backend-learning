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

&nbsp;    ↓

API Gateway

&nbsp;    ↓

Service Registry (Eureka)

&nbsp;    ↓

&nbsp;┌───────────┬────────────┬─────────────┐

&nbsp;| Quiz Svc      | Question   	  | Auth Svc        |

&nbsp;| (Spring)  	 | Service    	  | (JWT)           |

&nbsp;└───────────┴────────────┴─────────────┘

&nbsp;    ↓              ↓             ↓

&nbsp; Quiz DB     Question DB     User DB





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





