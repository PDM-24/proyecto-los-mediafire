
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

//OBTENER PELICULA POR ID
router.get("/moviesId/:id", movieController.getMovieById);

//rutas para comentar y obtener comentarios
router.post("/moviesId/:id/postComment", authenticate, commentController.postComment);
router.get("/moviesId/:id/comments", authenticate,commentController.getComments);
router.get("/moviesId/:id/comments/:parentId", authenticate,commentController.getRepliesToComment);

//ruta sobre calificacion

router.post("/moviesId/:id/rate", authenticate, movieController.rateMovie);
router.get("/moviesId/:movieId/average-rating", authenticate, movieController.getMovieAverageRating);
router.get("/topRatedMovies", authenticate, movieController.getTopRatedMoviesOverall);
//router.get("/getUpcomingMovies", authenticate, movieController.getUpcomingMovies);
 
router.post('/moviesId/:id/wishlist/add', authenticate,movieController.addToWishlist);
router.get('/wishlist/',authenticate, movieController.getWishlist);
//peliculas ya claificadas
router.get("/ratedMovies", authenticate, movieController.getRatedMovies);



//ADMIN
// Ruta para eliminar un comentario principal y sus respuestas
router.delete("/comments/:id", authenticate, commentController.deleteComment);

// Ruta para eliminar una respuesta específica
router.delete("/replies/:id", authenticate, commentController.deleteReply);


module.exports = router;