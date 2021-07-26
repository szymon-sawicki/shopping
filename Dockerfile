FROM openjdk:16

COPY /target/app.jar app.jar

ENTRYPOINT ["java", "-jar", "--enable-preview", "app.jar"]

EXPOSE 8000