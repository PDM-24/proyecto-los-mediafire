
const axios = require("axios");

const BASE_URL_API="https://api.themoviedb.org/3/"

const API_KEY="376635a2a15525fb5b00a5b6ac0f2861"

const User = require('../models/account.model');

const genreMap = {
    "accion": 28,
    "aventura": 12,
    "animacion": 16,
    "comedia": 35,
    "crimen": 80,
    "documental": 99,
    "drama": 18,
    "familia": 10751,
    "fantasia": 14,
    "historia": 36,
    "terror": 27,
    "musica": 10402,
    "misterio": 9648,
    "romance": 10749,
    "suspenso": 53,
    "belica": 10752,
    "western": 37
    // Agrega más géneros según necesites
  };

const getMovies=async()=>{

    try {
        const response = await axios.get(`${BASE_URL_API}discover/movie?api_key=${API_KEY}&language=es-MX`);
        return response.data;

    } catch (error) {
     
        throw new Error("Error occurred while creating the form. Please try again.");
    }
}



const normalizeString = (str) => {
  return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
};

const searchMovieByTitle = async (title, userId, sortBy = 'relevancia', genre = null) => {
  const normalizedTitle = normalizeString(title);

  try {
    const response = await axios.get(`${BASE_URL_API}search/movie?api_key=${API_KEY}&language=es-MX&query=${encodeURIComponent(normalizedTitle)}`);
    let movies = response.data.results;

    // Filtrar por género si se proporciona
    if (genre) {
      const genreId = genreMap[genre.toLowerCase()];
      if (genreId) {
        movies = movies.filter(movie => movie.genre_ids.includes(genreId));
      } else {
        throw new Error("Género no encontrado.");
      }
    }

    // Ordenar por el criterio especificado
    if (sortBy === 'mas recientes') {
      movies.sort((a, b) => new Date(b.release_date) - new Date(a.release_date));
    } else if (sortBy === 'mas antiguas') {
      movies.sort((a, b) => new Date(a.release_date) - new Date(b.release_date));
    }

    const moviesWithGenres = await Promise.all(movies.map(async (movie) => {
      const detailsResponse = await axios.get(`${BASE_URL_API}movie/${movie.id}?api_key=${API_KEY}&language=es-MX&append_to_response=videos`);
      const detailedMovie = detailsResponse.data;

      return {
        id: detailedMovie.id,
        poster: detailedMovie.poster_path ? `https://image.tmdb.org/t/p/w500${detailedMovie.poster_path}` : null,
        title: detailedMovie.title,
        duracion: detailedMovie.runtime,
        fecha_lanzamiento: detailedMovie.release_date,
        genero: detailedMovie.genres.map(genre => genre.name).join(", "),
        descripcion: detailedMovie.overview,
        trailer: detailedMovie.videos.results.length > 0 ? `https://www.youtube.com/watch?v=${detailedMovie.videos.results[0].key}` : null
      };
    }));

    // Guardar la búsqueda reciente del usuario
    const user = await User.findById(userId);
    if (user) {
      // Agregar la nueva búsqueda al inicio del array
      user.recentSearches.unshift({ query: normalizedTitle });
      // Limitar la lista a las últimas 5 búsquedas
      user.recentSearches = user.recentSearches.slice(0, 5);
      await user.save();
    }

    return moviesWithGenres;
  } catch (error) {
    throw new Error("Ocurrió un error al buscar la película. Por favor, inténtalo de nuevo.");
  }
};










const getGenreId = (genreName) => {
    const genreId = genreMap[genreName.toLowerCase()];
    if (!genreId) {
      throw new Error("Género no encontrado.");
    }
    return genreId;
  };
  

const getMoviesCategory = async (categoryName, limit = 10) => {
  try {
    const categoryId = getGenreId(categoryName);
    const response = await axios.get(
      `${BASE_URL_API}discover/movie?api_key=${API_KEY}&language=es-MX&with_genres=${categoryId}&page=1&include_adult=false&sort_by=popularity.desc&vote_count.gte=1000&vote_average.gte=5&with_watch_monetization_types=flatrate`
    );

    const movies = response.data.results.slice(0, limit); // Limitar las películas al número especificado

    // Enrich each movie with additional details including actors
    const moviesWithDetails = await Promise.all(
      movies.map(async (movie) => {
        // Obtener detalles de la película
        const detailsResponse = await axios.get(
          `${BASE_URL_API}movie/${movie.id}?api_key=${API_KEY}&language=es-MX&append_to_response=credits,videos`
        );
        const detailedMovie = detailsResponse.data;

        // Mapear los nombres y fotos de los actores
        const actors = detailedMovie.credits.cast.map((actor) => ({
          name: actor.name,
          profileUrl: actor.profile_path
            ? `https://image.tmdb.org/t/p/w500${actor.profile_path}`
            : null,
        }));

        return {
          id: detailedMovie.id,
          poster: detailedMovie.poster_path
            ? `https://image.tmdb.org/t/p/w500${detailedMovie.poster_path}`
            : null,
          title: detailedMovie.title,
          duracion: detailedMovie.runtime,
          fecha_lanzamiento: detailedMovie.release_date,
          genero: detailedMovie.genres.map((genre) => genre.name).join(", "),
          descripcion: detailedMovie.overview,
          trailer:
            detailedMovie.videos.results.length > 0
              ? `https://www.youtube.com/watch?v=${detailedMovie.videos.results[0].key}`
              : null,
          actors: actors,
        };
      })
    );




    
    return moviesWithDetails.sort(() => Math.random() - 0.5); // Reordenar aleatoriamente las películas
  } catch (error) {
    throw new Error(`No se ha podido encontrar películas en la categoría: ${categoryName}`);
  }
};





const getMoviesBySortType = async (sortType, limit = 10) => {
  try {
    const genreIds = Object.values(genreMap);
    let allMovies = [];

    for (const genreId of genreIds) {
      const response = await axios.get(`${BASE_URL_API}discover/movie?api_key=${API_KEY}&language=es-MX&with_genres=${genreId}&page=1&include_adult=false&sort_by=${sortType}&vote_count.gte=1000&vote_average.gte=5&with_watch_monetization_types=flatrate`);
      const movies = response.data.results.slice(0, limit);

      // Enrich each movie with additional details
      const moviesWithDetails = await Promise.all(movies.map(async (movie) => {
        const detailsResponse = await axios.get(`${BASE_URL_API}movie/${movie.id}?api_key=${API_KEY}&language=es-MX&append_to_response=videos,credits`); // Agregar credits para obtener los actores
        const detailedMovie = detailsResponse.data;

        // Obtener nombres y fotos de los actores
        const actors = detailedMovie.credits.cast.map(actor => ({
          name: actor.name,
          profilePhoto: actor.profile_path ? `https://image.tmdb.org/t/p/w500${actor.profile_path}` : null
        }));

        return {
          id: detailedMovie.id,
          poster: detailedMovie.poster_path ? `https://image.tmdb.org/t/p/w500${detailedMovie.poster_path}` : null,
          title: detailedMovie.title,
          duracion: detailedMovie.runtime,
          fecha_lanzamiento: detailedMovie.release_date,
          genero: detailedMovie.genres.map(genre => genre.name).join(", "),
          descripcion: detailedMovie.overview,
          trailer: detailedMovie.videos.results.length > 0 ? `https://www.youtube.com/watch?v=${detailedMovie.videos.results[0].key}` : null,
          actors: actors // Agregar información de los actores al objeto de la película
        };
      }));

      allMovies = allMovies.concat(moviesWithDetails);
    }

    return allMovies;
  } catch (error) {
    throw new Error("No se ha podido encontrar las películas.");
  }
};

  const getMostViewedMovies = async (limit = 10) => {
    return getMoviesBySortType('popularity.desc', limit);
  };
  
  const getMostRecentMovies = async (limit = 10) => {
    return getMoviesBySortType('release_date.desc', limit);
  };
  


  //por id
  const fetchMovieById = async (id) => {
    try {
      const response = await axios.get(`${BASE_URL_API}movie/${id}?api_key=${API_KEY}&language=es-MX&append_to_response=videos,credits`);
      const movie = response.data;
  
      const actors = movie.credits.cast.map(actor => ({
        name: actor.name,
        profileUrl: actor.profile_path ? `https://image.tmdb.org/t/p/w500${actor.profile_path}` : null,
      }));
  
      return {
        id: movie.id,
        poster: movie.poster_path ? `https://image.tmdb.org/t/p/w500${movie.poster_path}` : null,
        title: movie.title,
        duracion: movie.runtime,
        fecha_lanzamiento: movie.release_date,
        genero: movie.genres.map(genre => genre.name).join(", "),
        descripcion: movie.overview,
        trailer: movie.videos.results.length > 0 ? `https://www.youtube.com/watch?v=${movie.videos.results[0].key}` : null,
        actors: actors,
      };
    } catch (error) {
      throw new Error("Error occurred while fetching movie details. Please try again.");
    }
  };


  const getMovieAverageRating = async (movieId) => {
    try {
      const users = await User.find({ 'ratings.movieId': movieId });
      const ratings = users.map(user => {
        const ratingObj = user.ratings.find(r => r.movieId === movieId);
        return ratingObj ? ratingObj.rating : null;
      }).filter(r => r !== null);
  
      if (ratings.length === 0) return null;
  
      const averageRating = ratings.reduce((sum, rating) => sum + rating, 0) / ratings.length;
      return averageRating;
    } catch (error) {
      throw new Error("Error occurred while calculating the average rating. Please try again.");
    }
  };
  

// se exportan como se deven son variables por asi asi
module.exports={
    getMoviesAPI:getMovies,
    getMoviesCategoryAPI:getMoviesCategory,
      getMostViewedMoviesAPI: getMostViewedMovies,
  getMostRecentMoviesAPI: getMostRecentMovies,
  searchMovieByTitleAPI: searchMovieByTitle,
  fetchMovieByIdAPI:fetchMovieById,
  getMovieAverageRatingAPI:getMovieAverageRating
}
