# shopping-app

- PROJECT IS UNFINISHED -

Simple app used to train java collections skills and TDD. This is not real business case, i made it to have some fun with maps and their testing.
Data will be loaded from json files, I used my own Validator

### Project structure

By planing of structure I was inspired by DDD approach.

Domain objects:

- Customer
- Product
- Shopping - represents shopping activities of customer. Class contains two fields : Customer and list of products

Service

- ShoppingService. Class contains list of files from which data will be loaded and map (key - customer, value - another map where product is key and count of that products)

Config

- base classes and interfaces like Validator, JsonConverter

### Technology stack

- Java 16
- Junit5
- AssertJ
- Gson  
- Lombok

Build tool: Maven

### TODO

next tasks:

- write one or two additional tests of initialization method in ShoppingService
- at first tests and then implementation of methods calculating sum for shopping
- write method finding customer with highest price for shopping

* implementation of another service methods with tests
* make rest api with Spark web framework
