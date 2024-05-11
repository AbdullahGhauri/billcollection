FROM openjdk:17-jdk
COPY target/inventorydistribution-0.0.1-SNAPSHOT.jar .
EXPOSE 8090
ENTRYPOINT ["java","-jar","inventorydistribution-0.0.1-SNAPSHOT.jar"]