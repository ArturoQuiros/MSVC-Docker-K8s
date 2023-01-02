FROM openjdk:20-ea-19-slim-buster

WORKDIR /app

COPY ./target/MSVC-Usuarios-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "MSVC-Usuarios-0.0.1-SNAPSHOT.jar"]