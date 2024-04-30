//Ruta de crear usuario

const express = require("express");

const router = express.Router(); //enrutador
const AccountController = require("../controllers/account.controller");

 
//api/account/register
router.post("/register",AccountController.register);
router.post("/login",AccountController.login)
 module.exports = router;
