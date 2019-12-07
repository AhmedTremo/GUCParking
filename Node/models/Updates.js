const mongoose = require("mongoose");

const UpdateSchema = new mongoose.Schema({
 update: {
 type: String
 }
});

const Update = mongoose.model("Updates", userSchema);
module.exports = Update;
