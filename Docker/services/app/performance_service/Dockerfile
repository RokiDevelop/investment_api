FROM openjdk:17-alpine3.14
MAINTAINER Roman Kiryukhin
ARG JAR_FILE=*.jar
COPY ./${JAR_FILE} performance-service.jar
ENTRYPOINT ["java", "-jar", "performance-service.jar"]
EXPOSE 80