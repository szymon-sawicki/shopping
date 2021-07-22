FROM openjdk:16

EXPOSE 8000

ADD ./target/app.jar app.jar

ENTRYPOINT ["java", "-jar", "--enable-preview", "app.jar"]
