FROM node:10.15.0-alpine as front
COPY ["client", "client/"]
WORKDIR "/client"
RUN npm install
RUN npm run-script build

FROM openjdk:8-jdk-alpine
COPY ["server", "server/"]
RUN wget -O maven.tar.gz 'https://apache.volia.net/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz'
RUN tar -xzvf maven.tar.gz
RUN export PATH=$PATH:/*path to maven/bin*
WORKDIR "/server"
RUN mvn compile && mvn package
RUN mv /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
