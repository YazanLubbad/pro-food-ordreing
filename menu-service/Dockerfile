FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ENV PORT 9092
EXPOSE 9092
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} menu_service.jar
ENTRYPOINT ["java","-jar","/provider-service.jar"]