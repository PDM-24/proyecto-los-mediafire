const controller = {};//encargado de contener la informacion
const Movie = require("../models/movieData.model");
const MoviesService = require("../services/movies.services");

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


//Buscar pelicula

controller.searchTitle = async (req, res, next) => {
  try {
      const title = req.params.title;
      const movie = await MoviesService.searchTitleAPI(title);
      if (!movie || movie.length === 0) {
          throw new Error("No se encontraron películas con el título especificado.");
      }
      return res.status(200).json({ data: movie });
  } catch (error) {
      next(error);
  }
};


// controller.categoryMovie = async (req, res, next) => {
//   try {
//       const genreName=req.params.genreName;
//     const movies = await MoviesService.getMoviesCategoryAPI(genreName);
//     if (!movies || movies.length === 0) {
//           throw new Error("No se encontraron películas con el título especificado.");
//       }
//       return res.status(200).json({ data: movies });
//   } catch (error) {
//       next(error);
//   }
// };

controller.categoryMovie = async (req, res, next) => {
  try {
    const categoryName = req.params.categoryName;
    const movies = await MoviesService.getMoviesCategoryAPI(categoryName);
    if (!movies || movies.length === 0) {
      throw new Error("No se encontraron películas en la categoría especificada.");
    }
    return res.status(200).json({ data: movies });
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