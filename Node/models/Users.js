const mongoose = require("mongoose");

const userSchema = new mongoose.Schema({
 username: {
 type: String
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
      type: Boolean,
      required: true,
      default: false
  }
});

const User = mongoose.model("User", userSchema);
module.exports = User;
