const mongoose = require("mongoose");

const userSchema = new mongoose.Schema({
 username: {
 type: String,
 required: true
 },
  email: {
    type: String,
    required: true,
    unique: true,
    trim: true,
    lowercase: true,
    match: /^\S+@\S+\.\S+$/
  },
  password: {
    type: String,
    required: true
  },
  status: {
	type: boolean,
	required: true
  }
});

const User = mongoose.model("User", userSchema);
module.exports = User;
