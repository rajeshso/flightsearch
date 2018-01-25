**Flight Search**

The system can be compiled by
```mvn clean install ```
This would also execute the unit tests.

The application is built using Java 8, Spring Boot 1.5.8, mockito 1.9.5.

The application can be executed through the main method of FlightsearchApplication.

As soon as the application is up and running, the application can be reached using a REST client.

The following POST request 
```$json
{
  "time" : "06:00AM"
}
```
in ``` http://localhost:8090/api/flight``` results in 
``` 
{
    "flights": [
        {
            "flight": "Air Canada 8099",
            "departure": "7:30AM"
        },
        {
            "flight": "United Airline 6115",
            "departure": "10:30AM"
        }
    ],
    "additionalProperties": {}
}
```

_Code Organization_

The Code is a simple Spring Boot application with REST.
There are three packages - web, business and domain.
The web is a REST controller
The business is the place the flight search algorithm resides
The domain has Flight domain objects and the data source.

_External Reference_

The candidate had to make some external references. They 
are documented in the places. The most frequent sites are
spring initializr, java api doc, maven repo, stackoverflow 
(to convert date to local time). The exact place of references
are also mentioned in the code.

_Limitation_ 

When the time is between 12:00 AM to 12:59 AM (00:00 hours), 
the algorithm gets confused.  The solution for this case 
could not be completed in the permitted timeline. However,
the code works for the given time table.