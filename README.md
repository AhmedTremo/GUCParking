# GUCParking

- GUCParking android app to help guc students with their parking.

- Login using Firebase authentication enabled (Email/password, Facebook, google).

## Docker

- Make sure you install docker
- run command 'docker-compose up' --> this should build to containers on for node and one for mongoDB and connect them together.
- To check the Nodejs is running, go to (yourip/8080), to get your ip run "docker-machine ip" command

## Config

- You will need a .env config file in the node app, containing both MONGO_USERNAME=yourUsername, MONGO_PASSWORD=yourPassword

## Dependencies

### Node
1. Express.js is a modular web framework for Node.js.
2. Mongoose is an Object Data Modeling (ODM) library for MongoDB and Node. js. It manages relationships between data, provides schema validation, and is used to translate between objects in code and the representation of those objects in MongoDB.
3. Body-Parser:  allows express to read the body and then parse that into a Json object that we can understand.

### Android
1. Firebase for facebook/google authentication
