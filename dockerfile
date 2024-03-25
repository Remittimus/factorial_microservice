FROM openjdk:17-jdk

COPY . /app

WORKDIR /app

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/factorial_microservice-latest.jar"]

