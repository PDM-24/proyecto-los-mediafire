//Ruta de crear usuario

const express = require("express");

const router = express.Router(); //enrutador
const createAccountController = require("../controllers/createAccount.controller");

 
//api/account/register
router.post("/register",createAccountController.register);


 module.exports = router;
