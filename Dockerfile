FROM openjdk:8

COPY target/Ussd_app-0.0.1-SNAPSHOT.jar Ussd_app-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "Ussd_app-0.0.1-SNAPSHOT.jar"]