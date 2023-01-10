FROM openjdk:19-jdk-alpine3.16 as builder

WORKDIR /app/msvc-cursos

COPY ./pom.xml /app
COPY ./msvc-cursos/.mvn ./.mvn
COPY ./msvc-cursos/mvnw .
COPY ./msvc-cursos/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline
COPY ./msvc-cursos/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:19-jdk-alpine3.16

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/msvc-cursos/target/MSVC-Cursos-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

CMD ["java", "-jar", "MSVC-Cursos-0.0.1-SNAPSHOT.jar"]
