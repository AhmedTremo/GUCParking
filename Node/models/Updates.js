//Updatestobepostedthroughtheupdatesactivity
const mongoose = require("mongoose");

const UpdateSchema = new mongoose.Schema({
    user: {
        type: Users,
        required: true
    },
    place: {
        type: String,
        enum: ['gate3', 'gate2','gate1','IncampusParking','gate4','gate5']
    }

});

const User = mongoose.model("User", userSchema);
module.exports = User;
