FROM openjdk:17-alpine3.14
MAINTAINER Roman Kiryukhin
ARG JAR_FILE=*.jar
COPY ./${JAR_FILE} gateway-service.jar
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]
EXPOSE 80