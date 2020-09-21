FROM maven as build
RUN mkdir -p /app
WORKDIR /app
ADD . /app
RUN mvn clean install
FROM openjdk:8
COPY --from=build /app/target/GowthamCrudExample-0.0.1-SNAPSHOT.jar /app/target/GowthamCrudExample-0.0.1-SNAPSHOT.jar
EXPOSE 8095
ENTRYPOINT ["java", "-jar", "/app/target/GowthamCrudExample-0.0.1-SNAPSHOT.jar"]