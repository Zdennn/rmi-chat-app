# Chat RMI Application

A simple chat application built using Java RMI (Remote Method Invocation) that allows multiple clients to communicate through a central server.

## Features

- Real-time messaging between clients
- Server manages client connections
- Graphical user interface for clients using Swing
- RMI-based communication for distributed architecture

## Project Structure

- `src/klient/`: Client-side code including GUI and service implementation
- `src/server/`: Server-side code including service implementation
- `src/shared/`: Shared interfaces for RMI communication
- `bin/`: Compiled class files

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Java RMI support (included in JDK)

## Building the Project

1. Ensure you have Java installed and `javac` is in your PATH.

2. Open a terminal in the project root directory.

3. Create the output directory (if it doesn't exist):
   ```
   mkdir bin
   ```

4. Compile the source files in the correct order:
   ```
   javac -d bin src/shared/*.java
   javac -d bin -cp bin src/server/*.java
   javac -d bin -cp bin src/klient/*.java
   ```

## Running the Application

*Note: Out of the box, this application is configured for local testing. Both the server and clients must be run on the same machine (localhost).*

### Start the Server

The server internally creates the RMI registry on port 8000, so no external registry setup is required.

1. Open a terminal and navigate to the project root.
2. Run the server:
   ```
   java -cp bin server.App
   ```
   The server will start on port 8000 and display "Server běží..." (Server is running...).

### Start Clients

1. Open another terminal for each client.
2. Run a client:
   ```
   java -cp bin klient.App
   ```
3. Enter a username when prompted.
4. Start chatting!

## Usage

- Clients connect to the server using RMI.
- Messages are broadcast to all connected clients.
- Close the client window to disconnect.

## Notes

- The server must be running before starting clients.
- All communication happens through RMI on port 8000.
- Currently, the client connects to `rmi://localhost:8000/klienti`. If you want to test the application across multiple computers on a LAN, you need to change `localhost` in `klient/App.java` to the actual IP address of the server machine before compiling.

The source code and GUI of this project are in Czech, as it was originally built for a local audience. This English README is provided to explain the app's logic and features to a broader audience.