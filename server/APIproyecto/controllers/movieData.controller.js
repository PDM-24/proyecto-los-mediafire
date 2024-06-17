const controller = {};//encargado de contener la informacion
const Movie = require("../models/movieData.model");
const MoviesService = require('../services/movies.services');
const User = require('../models/account.model');

const httpError = require("http-errors");

controller.movieData=async(req,res,next)=>{

    try{
        const{
        //campos de datos
        posterMovie,
        titleMovie,
        durationMovie,
        yearMovie,
        genereMovie,
        descriptionMovie,
        trailerMovie
    }=req.body;

    // se le asignan los campos respectivos creando asi un nuevo objeto 
    const newMovie=new Movie({
        //campos de datos
        posterMovie:posterMovie,
        titleMovie:titleMovie,
        durationMovie:durationMovie,
        yearMovie:yearMovie,
        genereMovie:genereMovie,
        descriptionMovie:descriptionMovie,
        trailerMovie:trailerMovie

});   

const movieSave = await newMovie.save();

if (!movieSave) {
    throw httpError(500, "No se ha podido guardar las peliculas");
  }
  res.status(200).json({ data: movieSave });

    }catch(error){
        next(error);

    }
};


//traer las peliculas
controller.findAll = async (req, res, next) => {
    try {
      const movies = await MoviesService.getMoviesAPI();

      // Verificamos si se han encontrado películas
      if (!movies || movies.length === 0) {
          // Si no se encuentra ninguna película, lanzamos un error 500
          throw httpError(500, "No se han encontrado películas");

      }      
      return res.status(200).json({ data: movies });
    } catch (error) {
      next(error);
    }
  };





controller.getMostViewedMovies = async (req, res, next) => {
  try {
      // Obtener el usuario autenticado desde req.user (gracias al middleware authenticate)
      const userId = req.user._id;

      // Buscar al usuario en la base de datos
      const user = await User.findById(userId);
      if (!user) {
          throw httpError(404, 'Usuario no encontrado');
      }

      const limit = 2; // Puedes ajustar este valor según tus necesidades
      const mostViewedMovies = await MoviesService.getMostViewedMoviesAPI(limit);

      res.status(200).json({moviesMostViews:mostViewedMovies});
  } catch (error) {
      next(error);
  }
};

controller.getMostRecentMovies = async (req, res, next) => {
  try {
      // Obtener el usuario autenticado desde req.user (gracias al middleware authenticate)
      const userId = req.user._id;

      // Buscar al usuario en la base de datos
      const user = await User.findById(userId);
      if (!user) {
          throw httpError(404, 'Usuario no encontrado');
      }

      const limit = 2; // Puedes ajustar este valor según tus necesidades
      const mostRecentMovies = await MoviesService.getMostRecentMoviesAPI(limit);

      res.status(200).json({moviesRecent:mostRecentMovies});
  } catch (error) {
      next(error);
  }
};





controller.searchMovieByTitle = async (req, res, next) => {
  try {
    const userId = req.user._id;

    // Buscar al usuario en la base de datos
    const user = await User.findById(userId);
    if (!user) {
      throw httpError(404, 'Usuario no encontrado');
    }

    const { title } = req.params;

    // Buscar películas según el título proporcionado
    const movies = await MoviesService.searchMovieByTitleAPI(title, userId);

    if (!movies || movies.length === 0) {
      throw httpError(404, "No se encontraron películas con el título especificado.");
    }

    // Devolver las películas encontradas como respuesta
    return res.status(200).json({ movies} );
  } catch (error) {
    next(error);
  }
};











// Obtener las películas más vistas
controller.getMostViewedMovies = async (req, res, next) => {
  try {
      const userId = req.user._id;

      const user = await User.findById(userId);
      if (!user) {
          throw httpError(404, 'Usuario no encontrado');
      }

      const limit = 2; 
      const mostViewedMovies = await MoviesService.getMostViewedMoviesAPI(limit);

      
      res.status(200).json({ moviesMostViews: mostViewedMovies });
  } catch (error) {
      next(error);
  }
};





controller.getMovieById = async (req, res, next) => {
  try {

 

    const { id } = req.params;

    // Obtener los detalles de la película por ID
    const movieDetails = await MoviesService.fetchMovieByIdAPI(id);
    if (!movieDetails || movieDetails.length === 0) {
      throw httpError(404, "No se encontraron películas.");
    }
    // Devolver los detalles de la película
    res.status(200).json( movieDetails);
  } catch (error) {
    next(error);
  }
};







  //Eliminar pelicula
  controller.deleteById = async (req, res, next) => {
    try {
      const { identifier } = req.params;
  
      const movieDeleteById = await Movie.findByIdAndDelete(identifier);
  
      if (!movieDeleteById) {
        throw httpError(500, "No se ha podido eliminar la pelicula");
      }
  
      return res
        .status(200)
        .json({ message: "se ha eliminado correctamente la pelicula" });
    } catch (error) {
      next(); 
    }
  };


  

module.exports = controller;