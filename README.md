# Ticket Booking System

## About The Project

This project is a POC for Event driven microservice architecture using Spring boot. The microservices demonstrates choreography based SAGA pattern. For this POC, Kafka is the center piece and is being used as a message bus.

This is a snippet of movie ticket booking system which focuses on booking a seat. There are 4 services involved:
	* [show-service] (https://github.com/nishantc19/ticket-booking-system/tree/master/show-service) is the main service in the project, which is responsible for creating a show, adding seats to the show and updating seat status. This service is also implementing database level concurrency control for avoiding duplicate seat bookings by multiple customers.
	* [kafka] (https://github.com/nishantc19/ticket-booking-system/tree/master/kafka) is more of a configuration service responsibile for creating kafka topics.
	* [booking-service] (https://github.com/nishantc19/ticket-booking-system/tree/master/booking-service) and [payment-service] (https://github.com/nishantc19/ticket-booking-system/tree/master/payment-service) are kind of mock services, just for the sake of completing the operation flow and demonstrating how microservices can communicate asynchronously.

## Getting started



