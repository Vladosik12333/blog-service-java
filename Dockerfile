FROM maven:3.9.9-amazoncorretto-23-alpine AS build

WORKDIR /build/

COPY pom.xml /build/

COPY src /build/src/

RUN mvn dependency:go-offline

RUN mvn package -DskipTests

FROM amazoncorretto:23-alpine-jdk

ARG JAR_FILE=/build/target/*.jar

COPY --from=build $JAR_FILE /opt/blog-service-java/app.jar

ENTRYPOINT ["java", "-jar", "/opt/blog-service-java/app.jar"]