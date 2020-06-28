FROM ubuntu:20.04

ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update && \
		apt-get -y install apt-utils && \
		apt-get -y install software-properties-common debconf-utils wget && \
        apt-get install -y openjdk-8-jdk && \
	    apt-get install -y ant && \
        apt-get install -y ca-certificates-java && \
        apt-get install -y curl && \
		apt-get clean && \
        update-ca-certificates -f && \
        rm -rf /var/lib/apt/lists/* && \
	    rm -rf /var/cache/oracle-jdk8-installer;

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

ENV NVM_DIR /usr/local/nvm
ENV NODE_VERSION 12.16.1

RUN curl --silent -o- https://raw.githubusercontent.com/creationix/nvm/v0.31.2/install.sh | bash

RUN /bin/bash -c "source $NVM_DIR/nvm.sh \
    && nvm install $NODE_VERSION \
    && nvm alias default $NODE_VERSION \
    && nvm use default"

ENV NODE_PATH $NVM_DIR/v$NODE_VERSION/lib/node_modules
ENV PATH $NVM_DIR/versions/node/v$NODE_VERSION/bin:$PATH

WORKDIR "/"
RUN wget -O maven.tar.gz 'https://apache.volia.net/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz'
RUN tar -xzvf maven.tar.gz
ENV PATH="/apache-maven-3.6.3/bin:${PATH}"

COPY ["server", "/server"]
COPY ["client", "/client"]

WORKDIR "/client"
RUN npm install
RUN ls /client

WORKDIR "/server"
RUN mvn compile && mvn package
RUN mv ./target/*.jar /app.jar

WORKDIR "/"
COPY ["run.sh", "run.sh"]
CMD /bin/sh ./run.sh
