
const express = require("express");
const router = express.Router(); //enrutador 

const userRouter = require ("./users.router");//exportar enrutador

const movieRouter=require("./movie.router")

//registrarse/iniciar sesion
router.use("/account",userRouter);

//api/data/movie
router.use("/movies",movieRouter);

module.exports = router;
