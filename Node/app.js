const express = require("express");
const app = express();
const PORT = 8080;
const mongoose = require("mongoose");
const bodyParser = require('body-parser');

const User = require("./models/Users");
const Update = require("./models/Update")


app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());


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
//getting all the updates
app.get("/updates", (req, res) => {
    Update.find()
        .then(Updates => res.json(Updates))
        .catch(err => res.status(404).json({ msg: 'no Updates found'}))
});
//Adding update
app.post("/addingUpdate", (req, res) => {
    const newupdate = new Update({
        update: req.body.update
    })
    newupdate.save()
        .then(update => res.send(update))
        .catch(err => res.status(400).send("cannot add update"))
})
// Sample use for the database
// get all users in database
app.get("/users", (req, res) => {
    User.find()
        .then(users => res.json(users))
        .catch(err => res.status(404).json({ msg: 'no Users found'}))
});

// create a user
app.post("/user/create", (req, res) => {
    const newUser = new User({
        email: req.body.email,
        password: req.body.password
    })
    newUser.save()
        .then(user => res.send("User saved successfully"))
        .catch(err => res.status(400).send("cannot create user"))
})
app.post("/", (req, res) => {
    User.findOne({ email: req.body.email })
        .then(user => {
            if (user) {
                return res
                    .status(400)
                    .send("A user is already registered with this email");
            }
            var user = req.body;
            User.create(user)
                .then(user => {
                    return res.json({ msg: "User created", data: user });
                })
                .catch(err => {
                    console.log(err);
                    return res.sendStatus(500);
                });
        })
        .catch(err => {
            console.log(err);
            return res.sendStatus(500);
        });
});



    app.listen(PORT, function () {
        console.log(`Listening on ${PORT}`);
    })