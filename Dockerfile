FROM eclipse-temurin:17.0.4.1_1-jre
EXPOSE 8080
ADD target/microAddUpdateDeleteWorkOrderApi-0.0.1-SNAPSHOT.jar microAddUpdateDeleteWorkOrderApi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/microAddUpdateDeleteWorkOrderApi-0.0.1-SNAPSHOT.jar","--spring.profiles.active=dev"]