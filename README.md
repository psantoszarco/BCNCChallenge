<h1 align="center"> BCNC Challenge </h1> <br>

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Build Process](#build-process)
- [Build With](#build-with)

## Introduction

Project developed in Spring Boot, using DDD for the BCNC Challenge technical test, in which a Prices table is generated, the CRUD that interacts with it is developed and a custom search method that accepts brand_id, product_id and a date as parameters is added.

## Features

The characteristics of the project are as follows

* Add a new price to the table
* Modify an existent price in the table
* Delete an existent price in the table
* Read all prices in the table
* Perform a custom search by brand_id, product_id and date
* Architecture test
* Integrated test usign an h2 database in memory
* Unit test cover 100% of the business code

## Build Process

* To execute the proyect: mvn exec:java -Dexec.mainClass=»com.inditex.ecommerce.EcommerceApplication
* To compile the proyect: mvn clean install
* To pass the unit test: mvn clean test

## Build With

* Domain-Driven Design: The code is separated into folders according to the following configuration.
    - Controller: External communication layer.
    - UseCase: Application business, includes the data mapper.
    - Domain: Application domain, includes
        - Exceptions: Exceptions returned by the system.
        - model: Model, including DTO and database entities
        - repository: Database communication layer.
        - services: Application services

* Testing
    - Architecture test: Architecture test with some basic rules to comply with DDD rules
    - Unit test: Unit tests covering functional units, by applying TDD for the realization of the project, 100% of the business code has been covered and almost 100% of the rest of the code.
    - Integration test: e2e test with a h2 dabatase in memory

* Jacoco
  jacoco has been added to generate a coverage report after each test run, this report is placed in target/site/index.html.
    