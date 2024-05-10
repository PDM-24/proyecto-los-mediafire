//Ruta de crear usuario

const express = require("express");

const router = express.Router(); //enrutador
const AccountController = require("../controllers/account.controller");
const {
  createAccountValidator,
} = require("../validators/createAccount.validator");
const validateAccount = require("../middlewares/index.middlewares");
const {
  loginAccountValidator,
} = require("../validators/loginAccount.validator");
//api/account/register
router.post(
  "/register",
  //el validador va primero
  createAccountValidator,
  validateAccount,
  AccountController.register
);
router.post(
  "/login",
  loginAccountValidator,
  validateAccount,
  AccountController.login
);
module.exports = router;
