
const axios = require("axios");

const BASE_URL_API="https://api.themoviedb.org/3/"

const API_KEY="376635a2a15525fb5b00a5b6ac0f2861"


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
        const response = await axios.get(`${BASE_URL_API}discover/movie?api_key=${API_KEY}&language=es-Es`);
        return response.data;

    } catch (error) {
     
        throw new Error("Error occurred while creating the form. Please try again.");
    }
}



const normalizeString = (str) => {
  return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
};




const searchMovieByTitle = async (title) => {
  const normalizedTitle = normalizeString(title);

  try {
      const response = await axios.get(`${BASE_URL_API}search/movie?api_key=${API_KEY}&language=es-ES&query=${encodeURIComponent(title)}`);
      const movies = response.data.results;

      const moviesWithGenres = await Promise.all(movies.map(async (movie) => {
          const detailsResponse = await axios.get(`${BASE_URL_API}movie/${movie.id}?api_key=${API_KEY}&language=es-ES&append_to_response=videos`);
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

      // Filtrar las películas que contienen la parte normalizada del título
      const filteredMovies = moviesWithGenres.filter(movie =>
          normalizeString(movie.title).includes(normalizedTitle)
      );

      return filteredMovies;
  } catch (error) {
      throw new Error("Error occurred while searching for the movie. Please try again.");
  }
};









const getGenreId = (genreName) => {
    const genreId = genreMap[genreName.toLowerCase()];
    if (!genreId) {
      throw new Error("Género no encontrado.");
    }
    return genreId;
  };
  



// const getMoviesCategory = async (categoryName, limit = 10) => {
//   try {
//       const categoryId = getGenreId(categoryName);
//       const response = await axios.get(`${BASE_URL_API}discover/movie?api_key=${API_KEY}&language=es-ES&with_genres=${categoryId}&page=1&include_adult=false&sort_by=popularity.desc&vote_count.gte=1000&vote_average.gte=5&with_watch_monetization_types=flatrate`);
//       const movies = response.data.results.slice(0, limit); // Limitar las películas al número especificado
//       return movies.sort(() => Math.random() - 0.5); // Reordenar aleatoriamente las películas
//   } catch (error) {
//       throw new Error("No se ha podido encontrar dicha categoría.");
//   }
// };

const getMoviesCategory = async (categoryName, limit = 10) => {
  try {
      const categoryId = getGenreId(categoryName);
      const response = await axios.get(`${BASE_URL_API}discover/movie?api_key=${API_KEY}&language=es-ES&with_genres=${categoryId}&page=1&include_adult=false&sort_by=popularity.desc&vote_count.gte=1000&vote_average.gte=5&with_watch_monetization_types=flatrate`);
      
      const movies = response.data.results.slice(0, limit); // Limitar las películas al número especificado

      // Enrich each movie with additional details
      const moviesWithDetails = await Promise.all(movies.map(async (movie) => {
          const detailsResponse = await axios.get(`${BASE_URL_API}movie/${movie.id}?api_key=${API_KEY}&language=es-ES&append_to_response=videos`);
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

      return moviesWithDetails.sort(() => Math.random() - 0.5); // Reordenar aleatoriamente las películas
  } catch (error) {
      throw new Error(`No se ha podido encontrar películas en la categoría: ${categoryName}`);
  }
}





const getMoviesBySortType = async (sortType, limit = 10) => {
    try {
      const genreIds = Object.values(genreMap);
      let allMovies = [];
      
      for (const genreId of genreIds) {
        const response = await axios.get(`${BASE_URL_API}discover/movie?api_key=${API_KEY}&language=es-ES&with_genres=${genreId}&page=1&include_adult=false&sort_by=${sortType}&vote_count.gte=1000&vote_average.gte=5&with_watch_monetization_types=flatrate`);
        const movies = response.data.results.slice(0, limit);
    
        // Enrich each movie with additional details
        const moviesWithDetails = await Promise.all(movies.map(async (movie) => {
          const detailsResponse = await axios.get(`${BASE_URL_API}movie/${movie.id}?api_key=${API_KEY}&language=es-ES&append_to_response=videos`);
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
  



// se exportan como se deven son variables por asi asi
module.exports={
    getMoviesAPI:getMovies,
    getMoviesCategoryAPI:getMoviesCategory,
      getMostViewedMoviesAPI: getMostViewedMovies,
  getMostRecentMoviesAPI: getMostRecentMovies,
  searchMovieByTitleAPI: searchMovieByTitle

}
