FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /usr/src/apps
COPY build/libs/*.jar notification-service.jar

EXPOSE 8083
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","/usr/src/apps/notification-service.jar"]