# Dispatch Load Balancer - Spring Boot Application



This project is a **Spring Boot application** designed to optimize the allocation of delivery orders to a fleet of vehicles based on their locations. The goal is to minimize the total travel distance while considering vehicle capacities and order priorities. The application utilizes the **Haversine formula** to calculate distances between geographic coordinates.

---

## **Technologies Used**
- **Java** 17
- **Spring Boot** 3.1.0
- **H2 Database** 2.1.2 (in-memory database for local development and testing)
- **Maven** 3.x (for dependency management)
- **Mockito** 5.x (for unit testing)
- **Swagger** (for API documentation)
- **Postman** (for API testing)

---

## **Features**
1. **Order Management**
   - Accepts delivery orders with details like `orderId`, `latitude`, `longitude`, `address`, `packageWeight`, and `priority`.
2. **Vehicle Management**
   - Accepts vehicle details with `vehicleId`, `capacity`, `currentLatitude`, `currentLongitude`, and `currentAddress`.
3. **Dispatch Optimization**
   - Assigns orders to vehicles based on **priority** and **capacity**.
   - Minimizes total travel distance using the **Haversine formula**.
4. **Error Handling**
   - Handles invalid input, overcapacity scenarios, and unassignable orders gracefully.

---

## **Implementation Details**

The application calculates distances using the **Haversine formula** and follows an order allocation strategy based on:
- **Priority**: High-priority orders are assigned first.
- **Capacity**: Orders are assigned to vehicles without exceeding their capacity.
- **Distance**: Orders are assigned to the closest available vehicle.

---

## **How to Run the Project**

### **Prerequisites**
- **Java 17** installed
- **Maven** installed

### **Steps to Run**
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/arunkr741/Dispatch-load-balancer.git
   cd DispatchLoadBalancer
   ```

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Run Tests**:
   To execute unit tests, run the following command:

   ```bash
   mvn test
   ```

5. **Access the Application**:
- Application runs at: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
---

## **API Endpoints**

### **1. Input Delivery Orders**
- **Endpoint**: `POST /api/dispatch/orders`
- **Request Body**:
  ```json
  {
    "orders": [
      {
        "orderId": "ODR001",
        "latitude": 13.2558,
        "longitude": 54.6394,
        "address": "HSR Layout, Bengaluru",
        "packageWeight": 20,
        "priority": "HIGH"
      }
    ]
  }
  ```
- **Response**:
  ```json
  {
    "message": "Delivery orders accepted.",
    "status": "success"
  }
  ```

### **2. Input Fleet Details**
- **Endpoint**: `POST /api/dispatch/vehicles`
- **Request Body**:
  ```json
  {
    "vehicles": [
      {
        "vehicleId": "VEH002",
        "capacity": 200,
        "currentLatitude": 12.3180,
        "currentLongitude": 76.6548,
        "currentAddress": "Sarjapur road, Sarjapur"
      }
    ]
  }
  ```
- **Response**:
  ```json
  {
    "message": "Vehicle details accepted.",
    "status": "success"
  }
  ```

### **3. Retrieve Dispatch Plan**
- **Endpoint**: `GET /api/dispatch/plan`
- **Response**:
  ```json
  {
    "status": "success",
    "message": "All orders assigned successfully",
    "dispatchPlan": [
      {
        "vehicleId": "VEH002",
        "totalLoad": 20,
        "totalDistance": "5.25 km",
        "assignedOrders": [
          {
            "orderId": "ODR001",
            "latitude": 12.3180,
            "longitude": 76.6548,
            "address": "HSR Layout, Bengaluru",
            "packageWeight": 20,
            "priority": "HIGH"
          }
        ]
      }
    ],
    "unassignedOrders": []
  }
  ```

---

## **File Structure**
```
dispatch-load-balancer/
├── src/
│   ├── main/
│   │   ├── java/com/dispatch/dispatchloadbalancer/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   ├── util/
│   │   │   └── DispatchLoadBalancerApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/java/com/dispatch/dispatchloadbalancer/
│       ├── controller/
│       ├── service/
│       └── DispatchLoadBalancerApplicationTests.java
├── pom.xml
└── README.md
```


