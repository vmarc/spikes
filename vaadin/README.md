The project is a standard Maven project. To run it from the command line,
type `mvn`, then open http://localhost:8080 in your browser.

To create a production build, call `mvn clean package -Pproduction`.
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/myapp-1.0-SNAPSHOT.jar` (NOTE, replace 
`myapp-1.0-SNAPSHOT.jar` with the name of your jar).
