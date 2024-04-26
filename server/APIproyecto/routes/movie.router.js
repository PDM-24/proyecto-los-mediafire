
const express = require("express");

const router = express.Router(); //enrutador
const movieController = require("../controllers/movieData.controller");


//api/data/movies
router.get("/movies",movieController.findAll);
router.get("/movies/:identifier",movieController.findOneById)
router.post("/movies/add",movieController.movieData)
router.delete("/movies/delete/:identifier",movieController.deleteById)


 module.exports = router;
