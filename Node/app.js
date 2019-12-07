const express = require("express");
const app = express();
const PORT = 8080;
const mongoose = require("mongoose");
const bodyParser = require('body-parser');

const User = require("./models/Users");

app.use(bodyParser.urlencoded({ extended: false }));

// DB config
const db = require('./config/keys_dev').mongoURI

mongoose
    .connect(
        db,
        {
            useNewUrlParser: true,
            useUnifiedTopology: true
        }
    )
    .then(() => console.log('MongoDB Connected'))
    .catch(err => console.log(err))


app.get("/", (req, res) => {
 res.send("Welcome to GUC Parking backend \n");
});

// Sample use for the database
// get all users in database
app.get("/users", (req, res) => {
    User.find()
        .then(users => res.json(users))
        .catch(err => res.status(404).json({ msg: 'no Users found'}))
});

// create a user
app.post("/user-create", (req, res) => {
    const user = new User(req.body)
    user.save()
      .then(user => {
          res.send("user saved to database");
      })
      .catch(err => {
          res.status(400).send("unable to save");
      })
});


app.listen(PORT, function() {
 console.log(`Listening on ${PORT}`);
});