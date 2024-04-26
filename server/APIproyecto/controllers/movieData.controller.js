const controller = {};//encargado de contener la informacion
const Movie = require("../models/movieData.model");
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
}


//traer las peliculas
controller.findAll = async (req, res, next) => {
    try {
      const movieFindAll = await Movie.find({ hidden: false }); //arreglo
      return res.status(200).json({ data: movieFindAll });
    } catch (error) {
      next(error);
    }
  };


  //traer solo una
  controller.findOneById = async (req, res, next) => {
    try {
      const { identifier } = req.params;
      const movieFindOneById = await Movie.findById(identifier);
  
      if (!movieFindOneById) {
        throw httpError(500, "No se ha podido obtener la pelicula");
      }
      return res.status(200).json({ data: movieFindOneById });
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
