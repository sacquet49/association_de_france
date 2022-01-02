FROM openjdk:17.0-jdk

COPY jar/application.properties application.properties
COPY jar/association_web.jar association_web.jar
ENTRYPOINT ["java","-jar","/association_web.jar", "--spring.config.location=application.properties"]
