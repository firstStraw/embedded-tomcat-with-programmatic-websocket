# embedded-tomcat-with-programmatic-websocket
Example with an embedded Tomcat server exposing a websocket endpoint configured programmatically.

# Build it
`mvn clean package`

# Run it
`java -jar target/embedded-tomcat-with-programmatic-websocket-0.1-SNAPSHOT.jar`

# Access it
Using your favorite websocket client (ex. Simple Websocket Client), access `ws://localhost:8080/echo`
