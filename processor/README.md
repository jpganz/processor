# Message processor 

This specific module is in charge to handle all the logic and processing for newly created messages.


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



## Running the tests

To run manually the test you must:

*Once in the root folder run the following command:

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

* [VIA BROWSER]http://localhost:port/index.html 

Front end page where a web socket connection is created and is refreshed with ONLY database stored events.

##### [GET]http://localhost:port/v1/consumer 

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



##### [POST]http://localhost:port/v1/entry 

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



## Built With

* [Spring Boot](https://spring.io/projects/spring-boot/) - The web framework used
* [Netflix Zuul](https://github.com/Netflix/zuul) - The enrouter/balancer framework
* [Netflix Eureka](https://github.com/Netflix/eureka) - The service discovery framework
* [RabbitMQ](https://www.rabbitmq.com/) - The messaging broker
* [MongoDB](https://www.mongodb.com/) - DBMS - best for json formats
* [Maven](https://maven.apache.org/) - Project build tool
* [Docker](https://www.docker.com/) - Containerization tool


## Authors

Juan Hernandez - https://github.com/jpganz

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file on each module for details

## Acknowledgments

* Thanks to the great developers who made all the previously mentioned frameworks available AND open source :)


###To Do

Add more integration tests (preferable with wiremock)
Add oauth server with proper scopes
Add proper shutdown of app and services
