
COURIER TRACKING SERVICE

- The Postman collection is located in the docs directory.
- Stores can be created using the "POST /store" endpoint in the collection.
- Couriers can be created via the "POST /courier" endpoint, and you can find the courier’s ID in the response.
- Courier’s location can be changed using the "PATCH /courier/{id}/location" endpoint.
- When a courier's location changes, calculate the courier’s total distance and create an event.
- The CourierLocationChangedEventListener checks the courier's location and time interval to log events.
- The courier’s total travel distance can be seen via "GET /courier/{id}/totalDistance".
- The courier's entrance logs can be seen via "GET /courier/{id}/logs".

Technologies 

- Java 17
- Maven
- Spring Boot
- Spring Data JPA
- H2 DB

For accessing h2 db console; http://localhost:8080/h2-console
