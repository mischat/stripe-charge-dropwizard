FROM jamesdbloom/docker-java8-maven

RUN mkdir /usr/local/stripe-charge-dropwizard/
RUN cd /usr/local/stripe-charge-dropwizard/
ADD . /usr/local/stripe-charge-dropwizard/

WORKDIR /usr/local/stripe-charge-dropwizard/
RUN ["mvn", "package"]

EXPOSE 8080

CMD ["java", "-jar", "target/stripe-charge-dropwizard-1.0-SNAPSHOT.jar", "server", "target/classes/stripe-charge-dropwizard.yml"]
