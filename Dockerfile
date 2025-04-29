# Dockerfile
FROM openjdk:17-jdk-slim

# Uygulama JAR'ını container içine kopyala
COPY target/freelance-marketplace-api-0.0.1-SNAPSHOT.jar app.jar

# Env file containera kopyala
COPY .env .env

# Uygulama portunu expose et
EXPOSE 8080

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "/app.jar"]
