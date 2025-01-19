# Use basic Ubuntu image
FROM ubuntu:22.04

# Setup Java 21, Maven and other necessary dependencies
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    openjdk-21-jdk \
    maven \
    && apt-get clean

# Setup Allure Commandline
RUN wget -q -O allure.zip https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.23.0/allure-commandline-2.23.0.zip \
    && unzip allure.zip -d /opt/allure \
    && ln -s /opt/allure/allure-2.23.0/bin/allure /usr/bin/allure \
    && rm allure.zip

# Setup Java and Maven environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=$MAVEN_HOME/bin:$PATH

# Setup container workdir
WORKDIR /usr/src/app

# Copy pom-file and sources
COPY pom.xml .
COPY src ./src

# Comile and install dependencies
RUN mvn dependency:resolve
RUN mvn verify clean

# Run the tests and generate Allure-report
CMD mvn test && allure generate target/allure-results -o target/allure-report && allure serve -p 8080 target/allure-report