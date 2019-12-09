const {
  MONGO_USERNAME,
  MONGO_PASSWORD
} = process.env;

exports.mongoURI = `mongodb+srv://${MONGO_USERNAME}:${MONGO_PASSWORD}@uscoders-8apxq.mongodb.net/test?retryWrites=true&w=majority`