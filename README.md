# Simple Socket Server Application

This project is a simple socket server application in Java. It consists of a server that performs basic mathematical operations (addition, subtraction, multiplication, and division) on request from a client. The application demonstrates basic client-server communication using Java sockets.

## Project Structure

- `Operation.java`: Defines the `Operation` class, which represents a mathematical operation with two operands.
- `Client.java`: Defines the `Client` class, which connects to the server, sends an operation, and receives the result.
- `Server.java`: Defines the `Server` class, which listens for client connections, performs the requested operation, and sends back the result.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed
- A text editor or an Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse

### Running the Server

1. Compile the `Server.java` file:
    ```sh
    javac Server.java
    ```

2. Run the server:
    ```sh
    java Server
    ```

   The server will start listening on port 1234.

### Running the Client

1. Compile the `Client.java` file:
    ```sh
    javac Client.java
    ```

2. Run the client:
    ```sh
    java Client
    ```

   The client will prompt you to enter an operation and two numbers. It will then send this information to the server, which performs the operation and returns the result.

## Code Explanation

### Operation.java

```java
import java.io.Serializable;

/**
 * The Operation class represents a mathematical operation with two operands.
 * This class implements Serializable to allow its instances to be sent over a network.
 */
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String operation; // The operation to be performed (e.g., "add", "subtract")
    private double number1;   // The first operand
    private double number2;   // The second operand

    /**
     * Constructs an Operation object with the specified operation and operands.
     * @param operation The operation to be performed.
     * @param number1 The first operand.
     * @param number2 The second operand.
     */
    public Operation(String operation, double number1, double number2) {
        this.operation = operation;
        this.number1 = number1;
        this.number2 = number2;
    }

    /**
     * Returns the operation to be performed.
     * @return The operation.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Returns the first operand.
     * @return The first operand.
     */
    public double getNumber1() {
        return number1;
    }

    /**
     * Returns the second operand.
     * @return The second operand.
     */
    public double getNumber2() {
        return number2;
    }
}
```

### Client.java

```java
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * The Client class is responsible for connecting to the server,
 * sending an operation, and receiving the result.
 */
public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter operation (add, subtract, multiply, divide): ");
            String operation = scanner.nextLine();
            System.out.print("Enter first number: ");
            double number1 = scanner.nextDouble();
            System.out.print("Enter second number: ");
            double number2 = scanner.nextDouble();

            Operation op = new Operation(operation, number1, number2);
            out.writeObject(op);

            double result = in.readDouble();
            System.out.println("Result: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Server.java

```java
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Server class listens for connections from clients,
 * performs the requested operation, and sends back the result.
 */
public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is listening on port 1234");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                    Operation op = (Operation) in.readObject();
                    double result = 0;

                    switch (op.getOperation().toLowerCase()) {
                        case "add":
                            result = op.getNumber1() + op.getNumber2();
                            break;
                        case "subtract":
                            result = op.getNumber1() - op.getNumber2();
                            break;
                        case "multiply":
                            result = op.getNumber1() * op.getNumber2();
                            break;
                        case "divide":
                            result = op.getNumber1() / op.getNumber2();
                            break;
                        default:
                            System.out.println("Invalid operation: " + op.getOperation());
                    }

                    out.writeDouble(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
