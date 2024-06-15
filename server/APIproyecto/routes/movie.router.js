
const express = require("express");

const router = express.Router(); //enrutador
const movieController = require("../controllers/movieData.controller");
const authenticate = require("../middlewares/authorization.middlewares");
const userLogin=require("../controllers/userLogin.controller")

//api/data/movies
router.get("/moviesAll",movieController.findAll);
router.post("/add",movieController.movieData)
router.delete("/delete/:identifier",movieController.deleteById)
router.get("/search/:title",movieController.searchTitle)
router.get("/category/:categoryName",movieController.categoryMovie)
 module.exports = router;
