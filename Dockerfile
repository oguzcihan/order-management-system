FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean compile install -DskipTests && \
    mvn clean package -DskipTests && \
    cp target/order-management-system-0.0.1-SNAPSHOT.jar target/order-management-system.jar


FROM openjdk:17-jdk-alpine
LABEL maintainer="ocihan"
WORKDIR /app
COPY --from=build /app/target/order-management-system.jar order-management-system.jar
ENTRYPOINT ["java", "-jar", "/app/order-management-system.jar"]
