FROM openjdk:17
COPY build/libs/*.jar notification-service.jar
ENTRYPOINT ["java","-jar","/notification-service.jar"]