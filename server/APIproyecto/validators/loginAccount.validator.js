const { body } = require("express-validator");
const validators = {};

let passRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,32})/;

validators.loginAccountValidator = [
 

  body("email")
    .notEmpty()
    .withMessage("Debes de completar el campo"),
   

  body("password")
    .notEmpty()
    .withMessage("Debes de completar el campo")
   


];

module.exports = validators;
