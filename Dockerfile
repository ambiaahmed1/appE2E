FROM openjdk:20
VOLUME /tmp
EXPOSE 8081
ADD target/webapp-0.0.1-SNAPSHOT.jar /webapp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/webapp-0.0.1-SNAPSHOT.jar"]