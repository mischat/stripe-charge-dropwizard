Stripe Charge Dropwizard
================

Intro
-------

A Simple Stripe Charge WebService written using Dropwizard version 0.8.4 in Java 8
Based on https://github.com/RichoDemus/Dropwizard-skeleton

This repository comes with a Dockerfile so that it can be easily deployed.
The dockerfile has been tested in AWS's Elastic Beanstalk.

Run
-----
```
mvn clean package && java -jar target/stripe-charge-dropwizard-1.0-SNAPSHOT.jar server target/classes/stripe-charge-dropwizard.yml 
```

Test
----
```
curl --data "token=tok_16hjoWGtbRVNhzkimYbDarRL&amount=16900&email=lame%40example.com"  http://localhost:8080/app/charge

```
Configure 
----------
Please copy target/classes/stripe-charge-dropwizard.yml.template to target/classes/stripe-charge-dropwizard.yml 
and then fill it out accordingly

```
coreAllowedOrigin: "CORS ALLOWED ORIGIN" - This can be one of a full domain like (http://mmt.me.uk) or null or *
stripeSecretKey: "YOUR STRIPE API SECRET"
```

URLs
----
* [POST endpoint](http://localhost:8080/app/charge)  
* [Admin page](http://localhost:8080/admin)

Misc
----
* Banner created with [Taag](http://patorjk.com/software/taag)
* Skeleton taken from [RichoDemus](https://github.com/RichoDemus/Dropwizard-skeleton)
