const { body } = require("express-validator");
const validators = {};

let passRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,32})/;

validators.createAccountValidator = [
  body("username")
    .notEmpty()
    .withMessage("Debes de completar el campo")
    .isLength({ min: 2, max: 15 }),

  body("email")
    .notEmpty()
    .withMessage("Debes de completar el campo")
    .isEmail()
    .withMessage("Correo electronico incorrecto")
    .isLength({ min: 5, max: 35 })
    .withMessage("Tiene que tener como minimo 5 caracteres"),

  body("password")
    .notEmpty()
    .withMessage("Debes de completar el campo")
    .matches(passRegex)
    .withMessage(
      "Tiene que contenener al menos ocho caracteres, incluido al menos un número, e incluye letras mayúsculas y minúsculas y caracteres especiales"
    ),

  body("year_nac")
    .notEmpty()
    .withMessage("Debes de completar el campo")
    .withMessage("no es fecha valida")
    .custom((value, { req }) => {
      const birthDate = new Date(value);
      const twelveYearsAgo = new Date();
      twelveYearsAgo.setFullYear(twelveYearsAgo.getFullYear() - 12);

      if (birthDate > twelveYearsAgo) {
          throw new Error("Debes tener al menos 12 años de edad.");
      }

      return true;
  }),

  body("genere")
    .notEmpty()
    .withMessage("Debes de completar el campo")
    .isLength({ min: 2, max: 15 }),

  body("movie_genere")
    .notEmpty()
    .withMessage("Debes seleccionar almenos 3 generos de peliculas"),

  body("avatar").notEmpty().withMessage("Debes de elegir un avatar"),
];

module.exports = validators;
