FROM openjdk:11
ADD target/your-app-name.jar your-app-name.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "your-app-name.jar"]
