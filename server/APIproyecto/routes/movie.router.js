
const express = require("express");

const router = express.Router(); //enrutador
const movieController = require("../controllers/movieData.controller");
const authenticate = require("../middlewares/authorization.middlewares");
const commentController=require("../controllers/commentUser.controller")
//api/data/movies
router.get("/moviesAll",movieController.findAll);
router.post("/add",movieController.movieData)
router.delete("/delete/:identifier",movieController.deleteById)

// Rutas nuevas para obtener las películas más vistas y más recientes (protegidas por autenticación)
router.get("/mostViewed", authenticate, movieController.getMostViewedMovies);
router.get("/recentMovies", authenticate, movieController.getMostRecentMovies);

// Nueva ruta para buscar películas por título (protegida por autenticación)
router.get("/search/:title", authenticate, movieController.searchMovieByTitle);
// router.get("/moviesId/:id", authenticate, movieController.getMovieById);
router.get("/moviesId/:id", movieController.getMovieById);
router.post("/moviesId/:id/postComment", authenticate, commentController.postComment);
router.get("/moviesId/:id/comments", authenticate,commentController.getComments);

module.exports = router;