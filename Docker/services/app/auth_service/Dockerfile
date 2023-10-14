FROM openjdk:17-alpine3.14
MAINTAINER Roman Kiryukhin
ARG JAR_FILE=*.jar
COPY ./${JAR_FILE} auth-service.jar
ENTRYPOINT ["java", "-jar", "auth-service.jar"]
EXPOSE 80