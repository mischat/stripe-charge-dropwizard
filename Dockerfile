FROM dockerfile/java:oracle-java8

# Install maven
RUN apt-get update  
RUN apt-get install -y maven

RUN cd /usr/local/stripe-charge-dropwizard/ && git clone https://github.com/mischat/stripe-charge-dropwizard.git

WORKDIR /usr/local/stripe-charge-dropwizard/
RUN ["mvn", "package"]

EXPOSE 8080

CMD ["/usr/lib/jvm/java-8-oracle/bin/java", "-jar", "target/stripe-charge-dropwizard-1.0-SNAPSHOT.jar", "server", "target/classes/stripe-charge-dropwizard.yml"]
