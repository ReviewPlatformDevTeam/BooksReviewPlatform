FROM node:10.15.0-alpine as front
COPY ["client", "client/"]
WORKDIR "/client"
RUN npm install
RUN npm run-script build
RUN ls /client

FROM openjdk:8-jdk-alpine
COPY ["server", "server/"]
COPY --from=front /client/build /server/src/main/resources/static
RUN wget -O maven.tar.gz 'https://apache.volia.net/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz'
RUN tar -xzvf maven.tar.gz
WORKDIR "/server"
ENV PATH="/apache-maven-3.6.3/bin:${PATH}"
RUN mvn compile && mvn package
RUN mv ./target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

