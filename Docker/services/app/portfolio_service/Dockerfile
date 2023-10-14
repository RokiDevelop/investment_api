FROM openjdk:17-alpine3.14
MAINTAINER Roman Kiryukhin
ARG JAR_FILE=*.jar
COPY ./${JAR_FILE} portfolio-service.jar
ENTRYPOINT ["java", "-jar", "portfolio-service.jar"]
EXPOSE 80