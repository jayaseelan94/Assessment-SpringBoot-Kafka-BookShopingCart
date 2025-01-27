# Kafka-SpringBoot-Microservice
This project demonstrates a microservices architecture for a Books shopping cart system using Kafka for inter-service communication.
The system consists of two microservices: `cartService` and `PaymentService`, which communicate via Kafka using the "order-placed" event.

## Features

- **Microservices Architecture**: Clear separation of concerns.
- **Kafka-based Communication**: Event-driven communication between microservices.
- **Error Handling and Retries**: Ensures handle by @ControllerAdvice in GlobalException.java.
- **Hazelcast Caching**: Usuing Hazelcast Catching all serviceImpl classes.
- **Logs Using logback**: Using LoggerConfig logback-spring.xml to capture all logs and API Request and Response log too.
- **Sample Code**: Includes producers, consumers, serializers, and deserializers.

## Prerequisites

The project uses the following technologies and libraries:

- Java
- Spring Boot
- Spring Data
- Spring Microservices
- Spring Swagger
- Spring Test Junit,Mokito
- Apache Kafka(Zookeeper)
- MySql Database
- HazelCast Catching
- logback logger


## Getting Started

Follow these steps to get the project up and running:

1. **Clone the repository** to your local machine:
2. **Set Up Kafka and Topic**:
   Before running the project, set up Apache Kafka and create a Kafka topic by following these steps:
     - Start ZooKeeper.
     - Start a Kafka broker.
     - Create a topic named "order-placed".
          
   These steps establish ZooKeeper, start a Kafka broker, and create the "order-placed" topic for microservices communication.
3. **Project Tables**:
   Below tables are used to delvelop and stored datas. 
      1. **Books** -> Product details  book_id,book_title,book_price,book_author,description.
      2. **Customer** -> Customer details customer_id,cutomer_name,customer_email,cutomer_phone,cutomer_address and so on.
      3. **Order_item** -> Order Item details sotred like order_item_id, order_quantity,order_price,book_id so on.
      4. **Order** -> finally Orders consolidate this table Order_id,total_price,order_date,status.
      
      5. **Payment** -> Payment Table is the created Kafka this talbe is order_id,order_date,order_total_price.
      
5. **Create an Order**:
    To create an order and view the results in the payment service's console and payment table, perform the following steps:
   - Make an HTTP POST request to `http://localhost:8083/order/create` using the `order.http` file or `http://localhost:8083/swagger-ui/index.html`
   - In the request body, provide order details in JSON format.
   
6. **Verify Results**:
   - Check the payment service's result logs/cart-application.log for cart services same logs/payemnt-application.log for payment processing messages.
   - once Cart order is created Kafka is triggered message on fiven topic and crete payment records. 
   - Inspect the payment database table to ensure that payment records are correctly stored.

## Usage

Once the microservices are running, they will communicate via Kafka using the "order-placed" event.

To simulate an "order-placed" event, use the provided `OrderPlacedProducer` class in the `cartService` microservice.

Monitor the console output for payment status messages in the `PaymentService` microservice.
