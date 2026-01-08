# FROM eclipse-temurin:17-jdk-jammy
# ADD docker_database_demo.jar docker_db_demo.jar
# ENTRYPOINT ["java","-jar","/docker_db_demo.jar"]


# Dockerfile for AWS ECS
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","-XX:MaxRAMPercentage=75.0","-jar","app.jar"]