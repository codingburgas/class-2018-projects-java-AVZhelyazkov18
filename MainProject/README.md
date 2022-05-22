## Sample Project
This is a sample Java 11 project with 3-layered architecture. The code is separated into
3 distinct logical groupings, namely controllers, services, and repositories. Each grouping has different concerns.
 - Controllers deal with data presentation.
 - Services deal with business logic.
 - Repositories deal with data retrieval, storage, and manipulation.
 
## Technologies
- Java 11
- Maven +3.9.3

## In order to run:
1. Install Java 11, link: https://java.com/en/download/help/windows_manual_download.html
2. Install Maven +3.9.3, link: https://maven.apache.org/install.html
3. In terminal, go to a folder that contains the source code
4. Run `mvn clean package` to package the app into a jar
5. Run `java -jar target/project-1.0-SNAPSHOT.jar`
6. At this point, the app fails since no "jdbc_url" is specified
