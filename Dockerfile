# Usamos Java 21 (coincide con tu pom.xml)
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el JAR generado al contenedor
COPY target/UserHistory-0.0.1-SNAPSHOT.jar app.jar

# Puerto que expondrá el contenedor
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","app.jar"]
