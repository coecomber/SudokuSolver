FROM openjdk:11

LABEL maintainer="joostvherwaarden@hotmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8100

# The application's jar file
ARG JAR_FILE

# Add the application's jar to the container
ADD target/sudokuSolver-0.0.1.jar target/app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]