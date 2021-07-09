# shopping-app

Simple app used to train java collections skills (mostly maps) and TDD. 
Data will be loaded from json files, I used own, external Validator
Maybe I will make web api with spark web framework, but i'm not sure at the moment

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

* ShoppingService: finish initialization method 
 
* at first method names and tests

* implementation of service methods