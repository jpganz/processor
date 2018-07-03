# Eureka 

Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of middle-tier servers.

[Netflix Eureka](https://github.com/Netflix/eureka)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.


### Prerequisites

What things you need to install the software and how to install them

```
Java 8
Maven 3+
```

### Dependencies

Verify Java and Maven are installed. You can do it by running the following commands:


```
java -version
```

This must return the java version with a 1.8 or greater

```
mvn -v
```

This must return the maven version with a 3.0 or greater



## Deployment

How to run this system?

Once you are on the folder path run 

```
mvn clean install
```

this will generate a jar file under the /target.

Then run the following:

```
java -jar processor.jar 
```
(or the given jar name)

This will run the processor on a randomly selected port

#Endpoints

No endpoints for this module.


## Properties

Check OPS.md file


## Authors

Juan Hernandez - https://github.com/jpganz

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file on each module for details

## Acknowledgments

* Thanks to the great developers who made all the previously mentioned frameworks available AND open source :)


