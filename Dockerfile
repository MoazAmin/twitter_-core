FROM openjdk:17.0.1

WORKDIR /app
COPY ./build/libs/playground-0.0.1-SNAPSHOT.jar /app/svc.jar

EXPOSE 8080

CMD echo "this is a test"
CMD java -jar svc.jar
