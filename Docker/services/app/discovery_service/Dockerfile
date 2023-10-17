FROM openjdk:17-alpine3.14
MAINTAINER Roman Kiryukhin
ARG JAR_FILE=*.jar
COPY ./${JAR_FILE} discovery-service.jar
ENTRYPOINT ["java", "-jar", "discovery-service.jar"]
EXPOSE 80