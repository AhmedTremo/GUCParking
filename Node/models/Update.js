const mongoose = require("mongoose");

const UpdateSchema = new mongoose.Schema({
 update: {
 type: String
 }
});

const Update = mongoose.model("Update", UpdateSchema);
module.exports = Update;
