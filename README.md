## Swagger
Swagger-ui is available under the **/docs** endpoint
[localhost:8080/docs](localhost:8080/docs)
## How to run the app
There is a little script to compile and run the app included
### Requirements
- If on Windows, JAVA_HOME needs to be set and added to PATH
- **AND** have git bash **OR** can run shell scripts

#### From the command line, run "[run.sh](run.sh)"

### Manually running the app
1. run _./mvnw clean install_
2. run _java -jar app-0.0.1.jar_


## Challenge
For this challenge I had to:
- fetch data (posts and comments) in the JSON format
at the start of the application
- add four endpoints:
    - get post by post id
    - get comments by post id
    - get posts with pagination
    - get posts that contain a string parameter in their titles
- add unit tests

