##### **JAR** : Java ARchive

A JAR file is a single compressed file that contains:

* Compiled Java classes (.class)
* Resources (config files, images, etc.)
* A META-INF folder (with metadata, manifest)



Basically:

JAR = a packaged Java application / library



🧩 What is a JAR?

JAR is a packaged Java application or library that bundles all compiled classes and resources into a single file, making distribution, deployment, and execution easy.



❓ Why do we need JAR?

Without JAR:

* Your project = hundreds of .class files
* Hard to move, share, deploy, and run



With JAR:

Everything is bundled into one file

Easy to:

* Distribute
* Deploy
* Run
* Reuse as a library





##### **🐳Docker**:



1\. Why Docker? (Real problem)

Because:

* Different OS
* Different Java version
* Different dependencies
* Different environment variables



Docker solves this by:

Packaging application + environment + dependencies into one unit = Container



So your Spring Boot app will run exactly the same on:

* Your laptop
* Testing server
* Production server
* AWS ECS



2\. What is Docker?

Docker = Containerization platform

Docker allows us to package a Spring Boot application with its dependencies and runtime into containers so it behaves the same in dev, test, and production, and integrates smoothly with CI/CD and cloud platforms like AWS ECS.



3\. Core components/Core Concepts?

| Term       | Meaning                             |

| ---------- | ----------------------------------- |

| Image      | Immutable blueprint of app          |

| Container  | Running instance of image           |

| Dockerfile | Instructions to build image         |

| Registry   | Storage for images (DockerHub, ECR) |



🧊 Image: Blueprint of application

Read-only template



📦 Container: Running instance of image

Like:

Image = class

Container = object



🧾 Dockerfile: Recipe to build image



🏪 Registry: Place where images are stored

Example:

* Docker Hub
* AWS ECR



5\. Docker vs Virtual Machine

| Docker                   | VM                      |

| ------------------------ | ----------------------- |

| Lightweight              | Heavy                   |

| Uses host OS kernel      | Has own OS              |

| Fast startup (seconds)   | Slow startup (minutes)  |

| Better for microservices | Heavy for microservices |





6\. Typical Dockerfile for Spring Boot:

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/\*.jar app.jar

EXPOSE 8080

ENTRYPOINT \["java","-jar","app.jar"]



Build: docker build -t quiz-app .



Run: docker run -p 8080:8080 quiz-app



###### **🏗️ Architecture:**



Spring Boot Microservices

       ↓

 Docker Image

       ↓

   AWS ECR

       ↓

   AWS ECS

       ↓

Load Balancer → Internet





###### **🎯 INTERVIEW QUESTIONS \& SCENARIOS**



Q1: Why Docker over traditional deployment?

Answer:

With Docker we eliminate environment mismatch, improve deployment speed, and ensure consistent behavior across machines and cloud. It also integrates directly with CI/CD and orchestration platforms like ECS or Kubernetes.



Q2: How does Docker help Microservices?

Answer:

Each microservice is packaged independently, deployed independently, scaled independently. No dependency conflicts.



Q3: How do you handle DB in Docker?

Scenario Answer:

We run PostgreSQL as a separate container and use environment variables to configure connection strings.



Q4: Difference between Image \& Container?

Image is static, container is running instance.



Q5: What is Docker Compose? When used?

Used for running multiple containers together — for example Spring Boot + PostgreSQL locally.



Q6: How does Docker fit into CI/CD?

Scenario:

GitHub → Build JAR → Build Image → Push to ECR → ECS pulls image → Deploy



Q7: What happens when container crashes?

If running on ECS/Kubernetes, orchestrator restarts container automatically.



Q8: What are volumes?

Used for persistent data, mainly databases.



Q9: Security with Docker?

Use minimal base images, avoid root user, scan images, keep secrets outside image(AWS Secret manager).



Q10: How do you reduce image size?

* Multi-stage build
* Smaller base images
* Remove unused layers



🧑‍💻 Real Scenario Question (Very Important)

Interviewer: Your Spring Boot service works locally but fails on ECS. What do you check?

Answer:

1. Container logs
2. Environment variables
3. Port mapping
4. DB connectivity
5. Memory limits
6. Security groups



Docker Components:



1️⃣ Docker Client:

This is what you interact with.

Commands like: docker build, docker run, docker ps

When you run a command Docker Client sends request to Docker Daemon.



2️⃣ Docker Daemon (Docker Engine)

This is the brain of Docker.

* Builds images
* Runs containers
* Manages networks
* Manages volumes

Runs as a background service.

**Interview line:** Docker Daemon is the background service that handles container lifecycle management.



3️⃣ Docker Image

Read-only template used to create containers.

Contains:

* OS layer
* Java runtime
* Application JAR
* Config



4️⃣ Docker Container

Note: Container has it's own network

Note: Container shares kernel with host.



5️⃣ Dockerfile: It defines step-by-step instructions for building an image.

6️⃣ Docker Registry: Storage for Docker images. DockerHub, AWS ECR

7️⃣ Docker Network: Allows containers to communicate. Microservices communication, App ↔ DB connection

8️⃣ Docker Volume: Persistent storage. Volumes allow data persistence beyond container lifecycle. Important for databases.



**Docker Commands:** https://github.com/teluskoOrg/Docker-Source-Code/blob/main/Docker\_Commands.md

Note: 

✅**docker build** uses a Dockerfile and produces clean, repeatable images suitable for automation and production.

❌**docker commit** captures a container's current state and is mainly for experimentation or debugging, not production.



Docker Image reference structure -

\[registry]/\[repository]:\[tag] -> docker.io/library/hello-world:latest

Registry: Where the image is stored (default is Docker Hub).

Repository: The project or namespace (e.g., library/hello-world).

Tag: Version or variant (e.g., latest, 1.25).

Note : docker.io/library/hello-world:latest is unique but hello-world is not

Image ID is unique

Run comand -> docker help

Whenever you run container container id is generated that's new everytime but Whenever you pull image from docker then that image id is always same

Client talks to docker. Docker contains Daemon(Processor) which manages images,containers,volumes,..etc.

Which are components of Docker?

-it : interactive mode

-dit : ditach mode



https://github.com/teluskoOrg/Docker-Source-Code/blob/main/Running\_SpringBoot\_App\_On\_Docker.md



docker run -p 8081:8081 telusko/rest-demo:v2

here, -p <port\_on\_your\_machine> : <port\_inside\_container/Tmcat\_port>



**DockerFile example -**
FROM eclipse-temurin:22-jdk

WORKDIR /app

COPY target/rest-demo.jar app.jar

EXPOSE 8081

ENTRYPOINT \["java","-jar","app.jar"]



Then commands to create image and run container:

docker build -t telusko/rest-demo:v2 .

docker run -p 8081:8081 telusko/rest-demo:v2





**Docker-composer example -**



version: "3.7"



services:

  app:

    build: .

    ports:

      - "8080:8080"

    networks:

      - abhi-network



  postgres:

    image: postgres:latest

    environment:

      POSTGRES\_USER: abhishek

      POSTGRES\_PASSWORD: a@123

      POSTGRES\_DB: studentDb

    ports:

      - "5433:5432"

    networks:

      - abhi-network

    volumes:

      - postgres-s-data:/var/lib/postgres/data



networks:

  abhi-network:

    driver: bridge



volumes:

  postgres-s-data:

