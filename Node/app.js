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
app.get("/user-create", (req, res) => {
    const user = new User({ username: "userTest" })
    
    user.save()
        .then(() => console.log("User Created"))
    res.send("User Created \n")
});


app.listen(PORT, function() {
 console.log(`Listening on ${PORT}`);
});