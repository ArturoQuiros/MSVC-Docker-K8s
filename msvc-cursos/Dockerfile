FROM openjdk:19-jdk-alpine3.16 as builder

ARG MSVC_NAME=msvc-cursos

WORKDIR /app/msvc-cursos

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline
COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:19-jdk-alpine3.16

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/msvc-cursos/target/MSVC-Cursos-0.0.1-SNAPSHOT.jar .

ENV PORT 8002

EXPOSE $PORT

CMD ["java", "-jar", "MSVC-Cursos-0.0.1-SNAPSHOT.jar"]
