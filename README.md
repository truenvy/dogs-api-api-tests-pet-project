# Dogs API test pet-project

## Build the project

```mvn clean compile```

Downloads the specs, generates the models and compiles the code.

## Test project structure

```
├── .sdlc                                 # contains any important pipeline script to build the project in a CI/CD application
└──  src/main/java                        # main Java folder
    └── org.truenvy.dogs                  # initial package strucure
        ├── asserters                     # contains all the custom asserters
        ├── config                        # contains configs with some test data and config provider
        ├── data                          # package to group all the data-related classes (no code inside here)
        │   ├── changeless                # stores the static constants, containing all the immutable information regarding the service
        │   └── factory                   # contains the builder classes for models, mappers, possibly some helpers
        ├── rest                          # package to group all the rest-related classes (no code inside here)
        │   ├── api                       # package to group all the api-related classes (no code inside here)
        │   │   └── <version>             # the rest-assured high level API clients implementation, using the API configuration
        │   ├── configuration             # package contains common rest-assured API configuration for the service
        │   │   └── <version>             # rest-assured API configuration per each version of API for the service, including the API groups implementation
        │   └── models                    # package to group all the model-related classes (no code inside here)
        │       └── <version>             # supportive optional package, contains additional models, that are not generated from the APIs, like query parameters groups, some common abstract DTOs, etc
        └── service                       # contains the wrappers for the API clients, generated from the REST specifications, ready to be used in the tests
```

## Design Patterns and Approaches

### Design Patterns

#### BaseTest Class

The BaseTest Class or Test Case Superclass is a testing pattern to reuse test-specific logic normally related to pre- and
post-conditions.

#### Object Mother/Data Factory Class

The Object Mother is a kind of class used in testing to help create example objects that you use for testing, where I
can use the Factory pattern to easily generate the example objects.


#### Builder

Builder is a creational design pattern, which allows the construction of complex objects step by step. It can be used to
organize any group of classes, but I commonly use it in Model and Query Parameter objects.

### Approaches

#### Auto-generate clients from OpenAPI specification

To avoid the re-implementation of the API calls to the microservices I will use the Open API specification file from
the services to auto-generate the base client.

#### Hiding auto-generated clients using custom asserters

I could use the client class in the test. In this case, I would add the correct return instead of the generic Response
from Rest-Assured. This is doable but will add more code maintenance, so I decided to add a new (last one) abstraction
layer.

The asserters abstraction will contain a set of methods interacting with the client methods, asserting it instantly and
returning itself for chaining the methods.

#### Separation of constant and dynamic data

I am defining here a separation of the data used: changeless and dynamic.

* changeless: constant data used in different classes
* dynamic: the implementation of the Object Mother/Data Factory class

## Run the tests

### Via command line

Run the following command in order to run the tests

```mvn clean test```

Run the following command in order to generate Allure report

```
allure serve target/allure-results
```

### Via Docker

Build a docker image based on the Dockerfile in the root directory

```
docker build -t dogs-api-tests .
```

Run the docker container based on the built image

```
docker run -it --rm -p 8080:8080 dogs-api-tests
```
