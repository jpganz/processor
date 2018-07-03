# Message processor system

This is the message processor system, receives, process and stores messages.

Includes a web socket page that shows inserted messages on real time.

The modules implemented are:

* Processor - Receive, enqueues, process and save messages
* Zuul - Balancer multiple instances of a same application based on the service discovery's register instances
* Eureka - Service discovery where new instances register


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Java 8
Maven 3+
Docker 16+
```

### Dependencies

Verify Java, Maven and Docker are installed. You can do it by running the following commands:


```
java -version
```

This must return the java version with a 1.8 or greater

```
mvn -v
```

This must return the maven version with a 3.0 or greater


```
docker version
```

This must return the docker version with a 16 or greater


## Running the tests

To run manually the test you must:

*Specify the module's root folder

```
cmd ~/processor/processor
or
cmd ~/processor/zuul
or
cmd ~/processor/eureka
```

and run the following command:

```
mvn test
```

### What are the tests?

The test are unit test designed to try every programming statement, solution and resulting state.



### Test structure

The test are structured in the following way:

```
@Test
public void testMethodName(){
    when(mockedService.doSomething()).thenReturn(expectedResult);
    Object myPossibleReturn =testingService.methodName();
    verify(mockedService, times(numberOfExpectedCalls)).doSomething();
    assertThat(myPossibleReturn, is(EXPECTED_VALUE));
}
```

## Deployment

How to run this system?

Once you are at the processor root folder then run the following command:

```
./build.sh
```

It will compile the projects, create the docker images and set it at the running docker.

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot/) - The web framework used
* [Netflix Zuul](https://github.com/Netflix/zuul) - The enrouter/balancer framework
* [Netflix Eureka](https://github.com/Netflix/eureka) - The service discovery framework
* [RabbitMQ](https://www.rabbitmq.com/) - The messaging broker
* [MongoDB](https://www.mongodb.com/) - DBMS - best for json formats
* [Maven](https://maven.apache.org/) - Project build tool
* [Docker](https://www.docker.com/) - Containerization tool

## Docker-compose file

The docker compose file is made in a way where all the common dependencies can be adjusted and configured properly for the desired environment.

It supports multiple instances of the processor module (as many as desired). With no extra configuration other than add the instance on this file, it can run manually as well, will join by default to zuul thanks to eureka discovery platform.

#Endpoints

* [VIA BROWSER]http://localhost:8762/index.html 

Front end page where a web socket connection is created and is refreshed with ONLY database stored events.

##### [GET]http://localhost:8762/v1/consumer 

Returns the list with all the messages previously store with the following format:

```
[
  {
    "message": "{\"key\" : \"value\"}",
    "created": "2018-07-02T17:06:09.071Z"
  }
]
```

* Where message contains the sent message.
* Created is the given Date and Timezone at the moment of the saving event.
* Expected result on success: 202 (ACCEPTED)
* Expected result on error: 503 (SERVICE_UNAVAILABLE)



##### [POST]http://localhost:8762/v1/entry 

Endpoint to save new messages on a json format.

* Expected result on success: 202 (ACCEPTED) -- Since we cannot ensure the resource was created, the return is 201
* Expected result on error: 503 (SERVICE_UNAVAILABLE)

Expected resource:
```
{
  "key": "value"
}
```

Expected returned result:
```
[Response Body]

{"a test" : "test message!"}
----------------------------
[Response Code]
202
```

## Properties

Check OPS.md file


## Authors

Juan Hernandez - https://github.com/jpganz

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file on each module for details

## Acknowledgments

* Thanks to the great developers who made all the previously mentioned frameworks available AND open source :)


