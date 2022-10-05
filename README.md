# Garage Management System API 

## How To Run
Just use Java 11 and build with Maven. There is no DB in this app. I used In-Memory Collections.

## About Service
This services main logic is park or leave a vehicle in a specified garage (10 slot). Also you can see the status of garage.
If a vehicle parks to our garage, the car will holds next slot. That slot can't use for parking.

I defined int[10] for garage. So empty slots have 0 value, holdings slots have 99. Just for knowledge of which slots are holding. 

## How to use API
You can import Postman collection "vodafone-collection.postman_collection.json" and directly send requests or Swagger is already implemented. 
If you want to use Swagger, just go to "http://localhost:8080/swagger-ui/index.html".

### Validation
There is a custom validation annotation about vehicle type. A vehicle must be case sensitive Car, Jeep or Truck.
Also I used Spring Validation for vehicle plate.  A vehicle plate size must be 10.

### Exception Handling
I used @ControllerAdvice for global handling. There are two custom exception in service. If a vehicle is already leaved, the vehicle cant leave again.
Other custom exception is no available slot or slots in garage.

### Logging
I just logged basic operations. (park, leave, status, how many slots are allocated for a vehicle, garage is full) 
