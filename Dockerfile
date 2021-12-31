FROM adoptopenjdk:11-jre-hotspot-bionic
ADD target/login-0.0.1-SNAPSHOT.jar login.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar" , "login.jar"]
