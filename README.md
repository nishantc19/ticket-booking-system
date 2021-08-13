# Ticket Booking System

## About The Project

This project is a POC for Event driven microservice architecture using Spring boot. The microservices demonstrates choreography based SAGA pattern. For this POC, Kafka is the center piece and is being used as a message bus.

This is a snippet of movie ticket booking system which focuses on booking a seat. There are 4 services involved:
* [show-service](https://github.com/nishantc19/ticket-booking-system/tree/master/show-service) is the main service in the project, which is responsible for creating a show, adding seats to the show and updating seat status. This service is also implementing database level concurrency control for avoiding duplicate seat bookings by multiple customers.
* [kafka](https://github.com/nishantc19/ticket-booking-system/tree/master/kafka) service is more of a configuration service responsibile for creating kafka topics.
* [booking-service](https://github.com/nishantc19/ticket-booking-system/tree/master/booking-service) and [payment-service](https://github.com/nishantc19/ticket-booking-system/tree/master/payment-service) are kind of mock services, just for the sake of completing the operation flow and demonstrating how microservices can communicate asynchronously.

## Prerequisites

This project demands basic understanding of how Java Spring boot API service works and how to build a Java web-applications using Maven. Since Kafka is the certer piece, theoritical knowledge is a must. Also, you should know how to clone a git repository on your local.

I have used [cURL](https://developer.ibm.com/articles/what-is-curl-command/) for talking with the services. You can also use Postman or other similar tools.

## Getting started

1. The project is using Java 11, so you need to configure the build path accordingly.
2. Clone the repository on your local.
3. Build the services.
4. Open the command prompt and go to the directory where Kafka is installed.
5. Start the Zookeeper using following command:
```bash
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```
6. Start the Kafka server using following command:
```bash
bin\windows\kafka-server-start.bat config\server.properties
```
7. Run the [kafka](https://github.com/nishantc19/ticket-booking-system/tree/master/kafka) service on the server. It will create all the required topics in Kafka.
8. Run rest of the services: [show-service](https://github.com/nishantc19/ticket-booking-system/tree/master/show-service), [payment-service](https://github.com/nishantc19/ticket-booking-system/tree/master/payment-service) and [booking-service](https://github.com/nishantc19/ticket-booking-system/tree/master/booking-service).

Note:
If you are using windows like me, and want to use cURL, you might want to [read this](https://developer.zendesk.com/documentation/developer-tools/getting-started/installing-and-using-curl/#installing-curl).
