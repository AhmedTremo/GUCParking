# GUCParking

- GUCParking android app to help guc students with their parking.

- Login using Firebase authentication enabled (Email/password, Facebook, google).

## Docker 
We are using 2 docker containers one for node and other for mongoDB both built on their latest official image on DockerHub.

to run NodeJS docker file execute the following 3 commands
1. to start docker
```bash
docker-machine start default
```
2. to build the image
```bash
docker image build -t imageName .
```
3. to run the image
```bash
docker run -d -p 8080:8080 imageName
```



## Docker-Compose

- run command 
```bash
docker-compose up --build
```
this should build the containers one for node and one for mongoDB and connect them together.
- To check the Nodejs is running, go to (yourIP/8080), to get your ip run command
```bash
docker-machine ip
```

## Config

- You will need a .env config file in the node app, containing both your mongoDB username & password.
- and example .env file will contain the following lines
```env
MONGO_USERNAME=yourUsername
MONGO_PASSWORD=yourPassword
```

## Dependencies

### Node
1. Express.js: is a modular web framework for Node.js.
2. Mongoose: is an Object Data Modeling (ODM) library for MongoDB and Node. js. It manages relationships between data, provides schema validation, and is used to translate between objects in code and the representation of those objects in MongoDB.
3. Body-Parser:  allows express to read the body and then parse that into a Json object that we can understand.
4. Nodemon(dev): is a tool that helps develop node. js based applications by automatically restarting the node application when file changes in the directory are detected.

### Android
1. Firebase for facebook/google authentication
