# pull official base image
FROM openjdk:11-jdk

COPY ./target/comunicacao-1.0-SNAPSHOT.jar /comunicacao.jar

CMD ["java", "-jar", "/comunicacao.jar"]
