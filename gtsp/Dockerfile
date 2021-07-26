FROM openjdk:15
ADD target/gtsp-0.0.1-SNAPSHOT.jar gtsp-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-Xdebug", "-Xnoagent","-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8081","-jar", "gtsp-0.0.1-SNAPSHOT.jar"]