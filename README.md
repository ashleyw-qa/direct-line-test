# Direct Line Tests

#### Requirements
    Java 8
    Maven
    Chrome
    Docker

## How to run the tests

The tests run in parallel and be controlled threadCount variable via the [pom.xml](pom.xml)

### via native chrome

Run 
    
    `mvn clean test`
    
### via a selenium hub
    
Run 
    
    `docker-compose -up -d`
    
    `mvn clean test -Dbrowser=remoteChrome`
    
    `docker-compose down`
    
The selenium hub uses the Selenium Chrome debug node and can be view on port 5800.

#### To view the running tests in docker
    
    Mac
    `Open Safari`
    `Type vnc://localhost:5800`
    `Enter`
    `A prompt will be displayed stating 'Do you want to allow this page to open "Screen Sharing.app"?'`
    `Select 'Allow'`
    
    VNC viewer
    `Download and Install VNC viewer from 'https://www.realvnc.com/en/connect/download/vnc/'`
    `Enter the VNC address localhost:5800`
    
The VNC password is "secret"
  
### via GNU Make

   Start the docker Selenium containers
    
    `make up`
    
   Run the tests via docker
    
    `make run-tests`
    
   Kill the running containers
    
    `make clean`
 
## If all else fails   
### via docker and docker

   The Tests will be mounted into a Java 8 Maven docker container 
   and will run against a selenium docker container, as per a CI environment.
   
   Run 
       
    `docker-compose -up -d`
   
    `docker-compose run tests`
   
    `docker-compose down`

   
    