This is the assignment project from Discovery. 
This is a basic banking transactional microservice built with sring-rest and spring-jpa
with an in memory HSQLDB Database. On reboot of the application, the database will be
cleaned out.

In order to build and run this project you must ensure you have the following installed and setup:

1. Maven - at least version 3.3
2. Java - at least version 1.8 (Has not been tested with 1.9)

To compile project please run the following from the root directory of the project:
    mvn clean install

The project is built with spring boot, and has an embedded tomcat server, so in order to run the project,
please execute the following command:
    mvn spring-boot:run

The project can be accessed from this URL:

    http://localhost:9090/bank-app --> This will load the swagger UI and allow you a view and test of all the endpoints

Some improvements:
1. Could definitely have added some integration and unit tests.
2. The reporting calculations may be slightly off. The assumption made was that for the net position we would
want to deduct the used credit balance from actual asset balances just like we would the loan accounts. But again,
I might have been entirely wrong with the calculations.
3. I know the response object is slightly bloated, but that can be improved.

ENJOY!!! 