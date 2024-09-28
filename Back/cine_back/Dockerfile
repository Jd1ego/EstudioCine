FROM ubuntu:latest
LABEL authors="sebas"

FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR a la imagen
COPY build/libs/*.jar app.jar

# Expone el puerto en el que la aplicación escucha
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8081"]

