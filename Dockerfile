FROM maven:3.9.9-amazoncorretto-23 AS build

COPY pom.xml /build/

WORKDIR /build/

RUN mvn dependency:go-offline

COPY src /build/src/

RUN mvn package -DskipTests

FROM amazoncorretto:23-jdk

ARG JAR_FILE=/build/target/*.jar

COPY --from=build $JAR_FILE /opt/blog-service-java/app.jar

ENTRYPOINT ["java", "-jar", "/opt/blog-service-java/app.jar"]