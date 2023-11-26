FROM openjdk:17-alpine3.14
MAINTAINER Roman Kiryukhin
ARG JAR_FILE=*.jar
COPY ./${JAR_FILE} market-service.jar
ENTRYPOINT ["java", "-jar", "market-service.jar"]
EXPOSE 80