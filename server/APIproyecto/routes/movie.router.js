
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
router.get("/moviesId/:id", movieController.getMovieById);

//rutas para comentar y obtener comentarios
router.post("/moviesId/:id/postComment", authenticate, commentController.postComment);
router.get("/moviesId/:id/comments", authenticate,commentController.getComments);
router.get("/moviesId/:id/comments/:parentId", authenticate,commentController.getRepliesToComment);

//ruta sobre calificacion

router.post("/moviesId/:id/rate", authenticate, movieController.rateMovie);
router.get("/moviesId/:movieId/average-rating", authenticate, movieController.getMovieAverageRating);


module.exports = router;