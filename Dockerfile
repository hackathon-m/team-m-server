#Dockerfile

FROM openjdk:17

COPY build/libs/*.jar hackathon-m.jar

ENTRYPOINT ["java", "-jar", "hackathon-m.jar"]