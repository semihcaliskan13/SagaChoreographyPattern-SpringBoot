# Saga-Choreography-Pattern-SpringBoot

# Microservices Project: Order-Stock-Payment

This is a microservices project written in Spring Boot, consisting of three services: OrderService, StockService, and PaymentService. The services communicate through a message queue (RabbitMQ) and share a common "Shared" folder.

## Services

### 1. OrderService
- Responsible for handling orders.
- Listens for requests at `/orders` endpoint.
- Emits `OrderCreatedEvent` when an order is created.
- Listens for `StockNotReservedEvent` and updates the order status to "Failed".
- Listens for `PaymentCompletedEvent` and updates the order status to "Completed".

### 2. StockService
- Manages stock information.
- Listens for `OrderCreatedEvent` and emits either `StockReservedEvent` or `StockNotReservedEvent`.
- Listens for `PaymentNotCompletedEvent` and reverts stock changes.

### 3. PaymentService
- Handles payment processing.
- Listens for `StockReservedEvent` and completes payment if the total price is less than 100.
- Emits `PaymentCompletedEvent` or `PaymentNotCompletedEvent` accordingly.

## Shared Folder
- Contains shared resources or common functionalities.

## Dependencies

- OrderService uses MySQL for data storage.
- StockService uses MongoDB for data storage.
- Message queue is provided by RabbitMQ.

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/microservices-project.git
   cd microservices-project

