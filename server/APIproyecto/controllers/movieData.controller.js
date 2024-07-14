
const controller = {};//encargado de contener la informacion
const Movie = require("../models/movieData.model");
const MoviesService = require('../services/movies.services');
const User = require('../models/account.model');

const httpError = require("http-errors");
const moviesServices = require("../services/movies.services");

controller.movieData=async(req,res,next)=>{

  try{
      const{
      //campos de datos
      title, synopsis, duration, actors, coverPhoto, categories 
  }=req.body;

  // se le asignan los campos respectivos creando asi un nuevo objeto 
  const newMovie=new Movie({
      //campos de datos
      title:title,
          synopsis:synopsis,
          duration:duration,
          actors:actors,
          coverPhoto:coverPhoto,
          categories:categories

});   

const movieSave = await newMovie.save();

if (!movieSave) {
  throw httpError(500, "No se ha podido guardar las peliculas");
}
res.status(200).json({ message:"Se ha creado la pelicula" });

  }catch(error){
      next(error);

  }
}

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


  controller.deleteMovie = async (req, res, next) => {
    try {
        const { id } = req.params; // Obtener el ID de la película a eliminar

        // Verificar si la película existe
        const movie = await Movie.findById(id);
        if (!movie) {
            throw httpError(404, 'Película no encontrada');
        }

        // Eliminar la película
        await Movie.findByIdAndDelete(id);

        // Respuesta exitosa
        res.status(200).json({ message: 'Película eliminada exitosamente' });
    } catch (error) {
        // Manejar errores y pasar al siguiente middleware de error
        next(error);
    }
};

controller.getMovieByAdminId = async (req, res, next) => {
  try {
    const { id } = req.params; // Obtener el ID de la película a obtener

    // Buscar la película por su campo id (auto-incremental)
    const movie = await Movie.findOne({ id: parseInt(id, 10) });

    // Verificar si la película existe
    if (!movie) {
      throw httpError(404, 'Película no encontrada');
    }

    // Respuesta exitosa con los datos de la película
    res.status(200).json(movie);
  } catch (error) {
    // Manejar errores y pasar al siguiente middleware de error
    next(error);
  }
};



controller.getAllMovies = async (req, res, next) => {
    try {
        // Obtener todas las películas
        const movies = await Movie.find();

        // Respuesta exitosa con las películas encontradas
        res.status(200).json({ data: movies });
    } catch (error) {
        // Manejar errores y pasar al siguiente middleware de error
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
    const { sortBy, genre } = req.query;

    // Buscar películas según el título proporcionado con filtros adicionales
    const movies = await MoviesService.searchMovieByTitleAPI(title, userId, sortBy, genre);

    if (!movies || movies.length === 0) {
      throw httpError(404, "No se encontraron películas con el título especificado.");
    }

    // Devolver las películas encontradas como respuesta
        return res.status(200).json({ moviesA:movies} );

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
    const userId = req.user._id;

    // Buscar al usuario en la base de datos
    const user = await User.findById(userId);
    if (!user) {
      throw httpError(404, 'Usuario no encontrado');
    }
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


// Obtener promedio de películas:
controller.getMovieAverageRating = async (req, res, next) => {
  try {
    const userId = req.user._id;

    // Buscar al usuario en la base de datos
    const user = await User.findById(userId);
    if (!user) {
      throw httpError(404, 'Usuario no encontrado');
    }
    const { movieId } = req.params;
    console.log(`Buscando calificaciones para la película con ID: ${movieId}`);

    const numericMovieId = Number(movieId);

    
    // Buscar todos los usuarios que han calificado esta película
    const users = await User.find({ 'ratings.movieId': movieId });
    console.log(`Usuarios encontrados: ${users.length}`);
    
    // Recoger las calificaciones y detalles de la película de cada usuario
       // Recoger la calificación más reciente de cada usuario para la película
       const latestRatings = users.flatMap(user => {
        const ratingsForMovie = user.ratings.filter(r => r.movieId === movieId);
        if (ratingsForMovie.length > 0) {
          // Obtener la calificación más reciente
          const latestRating = ratingsForMovie[ratingsForMovie.length - 1].rating;
          return [latestRating];
        }
        return [];
      });
      

    
    // Verificar si hay calificaciones disponibles
    if (latestRatings.length === 0) {
      return res.status(404).json({ message: 'No hay calificaciones para esta película' });
    }
    
   // Calcular el promedio de las calificaciones más recientes
   const averageRating = latestRatings.reduce((sum, rating) => sum + rating, 0) / latestRatings.length;
   console.log(`Promedio de calificaciones: ${averageRating}`);
   
    
    // Obtener los detalles de la primera película encontrada (asumiendo que todos los detalles son los mismos)
    //const movieDetails = averageRating[0].movieDetails;
    
    return res.status(200).json({ averageRating });
  } catch (error) {
    next(error);
  }
};





// Obtener películas calificadas POR USUARIO
controller.getRatedMovies = async (req, res, next) => {
  try {
    const userId = req.user._id; // Asumiendo que el ID del usuario está disponible en req.user
    const ratedMovies = await MoviesService.getRatedMoviesAPI(userId);
    res.status(200).json({ ratedMovies });
  } catch (error) {
    console.error("Error in controller.getRatedMovies:", error.message, error.stack);
    next(new Error("Error occurred while fetching rated movies. Please try again."));
  }
};





  


  controller.rateMovie = async (req, res, next) => {
    try {
      const { movieId, rating } = req.body;
      const userId = req.user._id;
  
      // Buscar al usuario en la base de datos
      const user = await User.findById(userId);
      if (!user) {
        throw httpError(404, 'Usuario no encontrado');
      }
  
      // Verificar si el usuario ya ha calificado esta película
      const existingRating = user.ratings.find(r => r.movieId === movieId);
  
      // Registrar la calificación existente antes de modificar
      console.log('Calificación existente:', existingRating);
  
      // Actualizar o agregar la calificación
      if (existingRating) {
        existingRating.rating = rating;
      } else {
        user.ratings.push({ movieId, rating });
      }
  
      // Guardar los cambios en el usuario
      await user.save();
  
      // Registrar todas las calificaciones después de guardar
      console.log('Todas las calificaciones del usuario:', user.ratings);
  
      // Responder con mensaje de éxito
      return res.status(200).json({ message: 'Calificación guardada correctamente' });
    } catch (error) {
      next(error);
    }
  };  


  controller.getTopRatedMoviesOverall = async (req, res, next) => {
    try {
      // Obtener todas las calificaciones de todos los usuarios
      const users = await User.find();
      const movieRatings = {};
  
      users.forEach(user => {
        user.ratings.forEach(rating => {
          if (!movieRatings[rating.movieId]) {
            movieRatings[rating.movieId] = [];
          }
          movieRatings[rating.movieId].push(rating.rating);
        });
      });
  
      // Calcular el promedio de calificaciones para cada película
      const averageRatings = Object.keys(movieRatings).map(movieId => {
        const ratings = movieRatings[movieId];
        const averageRating = ratings.reduce((sum, rating) => sum + rating, 0) / ratings.length;
        return { movieId, averageRating };
      });
  
      // Ordenar las películas por calificación promedio de mayor a menor
      averageRatings.sort((a, b) => b.averageRating - a.averageRating);
  
      // Limitar a las top N películas
      const topN = 10; // Puedes ajustar este valor según tus necesidades
      const topRatedMovieIds = averageRatings.slice(0, topN).map(item => item.movieId);
  
      // Obtener detalles de las películas mejor calificadas
      const moviesWithDetails = await Promise.all(topRatedMovieIds.map(async (movieId) => {
        const movieDetails = await MoviesService.fetchMovieByIdAPI(movieId);
        const averageRating = averageRatings.find(rating => rating.movieId === movieId).averageRating;
        return {
          id: movieDetails.id,
          poster: movieDetails.poster,
          title: movieDetails.title,
          duracion: movieDetails.duracion,
          fecha_lanzamiento: movieDetails.fecha_lanzamiento,
          genero: movieDetails.genero,
          descripcion: movieDetails.descripcion,
          trailer: movieDetails.trailer,
          averageRating // Incluir el promedio de calificación
        };
      }));
  
      // Devolver las películas mejor calificadas por todos los usuarios
      res.status(200).json({ topRatedMovies: moviesWithDetails });
    } catch (error) {
      next(error);
    }
  };





  controller.addToWishlist = async (req, res) => {
    const userId = req.user.id;  // Obtener el userId del token de autenticación
    const { movieId } = req.body;
  
    try {
      const result = await MoviesService.addToWishlistAPI(userId, movieId);
      res.status(200).json(result);
    } catch (error) {
      res.status(500).json({ message: error.message });
    }
  };
  
 



  //obtener Wathclist

  controller.getWishlist = async (req, res) => {
    const userId = req.user.id;  // Obtener el userId del token de autenticación
  
    try {
      const wishlist = await MoviesService.getWishlistAPI(userId);
      res.status(200).json({wishlist:wishlist});
    } catch (error) {
      res.status(500).json({ message: error.message });
    }
  };


  controller.searchActorsByName = async (req, res, next) => {
    try {
      const { actorName } = req.params;
  
      // Llama a la función para buscar actores por nombre desde el servicio de películas
      const actors = await MoviesService.searchActorsByNameAPI(actorName);
  
      if (!actors || actors.length === 0) {
        throw httpError(404, "No se encontraron actores con el nombre especificado.");
      }
  
      res.status(200).json({ actors: actors });
  
    } catch (error) {
      next(error);
    }
  };


  controller.getUserRatingsForMovie = async (req, res, next) => {
    try {
      const { movieId } = req.params;
      const userId = req.user._id; // Asumiendo que el middleware de autenticación añade el usuario a la solicitud
  
      // Obtener la calificación del usuario actual para la película con movieId
      const userRating = await moviesServices.getUserRatingsForMovieAPI(userId, movieId);
  
      if (userRating) {
        // Devolver la calificación del usuario como respuesta
        res.status(200).json({ userRating });
      } else {
        // Si no se encuentra la calificación, devolver un 404
        res.status(404).json({ message: "No rating found for the specified movie." });
      }
    } catch (error) {
      next(error);
    }
  };

module.exports = controller;

