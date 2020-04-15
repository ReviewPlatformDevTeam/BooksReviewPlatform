FROM node:10.15.0-alpine as front
COPY ["client", "client/"]
WORKDIR "/client"
RUN npm install
RUN npm run-script build

FROM openjdk:8-jdk-alpine
COPY ["master", "master/"]
RUN wget -O maven.tar.gz 'https://apache.volia.net/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz'
RUN tar -xzvf maven.tar.gz
RUN export PATH=$PATH:/maven/bin*
WORKDIR "/master"
RUN cp ../client/build ./server/src/main/resources
RUN mv ./server/src/main/resources/build ./server/src/main/resources/static
RUN mvn compile && mvn package
RUN mv ./target/*.jar ./target/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
