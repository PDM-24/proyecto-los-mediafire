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
    "cienciaFiccion": 878,
    "peliculaTV": 10770,
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

const searchTitle = async (title) => {
    try {
        const response = await axios.get(`${BASE_URL_API}search/movie?api_key=${API_KEY}&query=${encodeURIComponent(title)}&language=es-Es`);
        return response.data.results;
    } catch (error) {
        throw new Error("El titulo de la pelicula no se ha encontrado.");
    }
};


const getGenreId = (genreName) => {
    const genreId = genreMap[genreName.toLowerCase()];
    if (!genreId) {
      throw new Error("Género no encontrado.");
    }
    return genreId;
  };
  


// const getMoviesCategory = async (categoryId) => {
//     try {
//       const response = await axios.get(`${BASE_URL_API}discover/movie?api_key=${API_KEY}&language=es-Es&with_genres=${categoryId}`
//       );
//       return response.data.results;
//     } catch (error) {
//       throw new Error(
//         "No se ha podido encontrar dicha categoria."
//       );
//     }
//   };

const getMoviesCategory = async (categoryName) => {
    try {
      const categoryId = getGenreId(categoryName);
      const response = await axios.get(`${BASE_URL_API}discover/movie?api_key=${API_KEY}&language=es-Es&with_genres=${categoryId}`);
      const movies = response.data.results;

// Reordenar aleatoriamente las películas
const randomMoviesGeneres = movies.sort(() => Math.random() - 0.5);
    
return randomMoviesGeneres;
    } catch (error) {
      throw new Error("No se ha podido encontrar dicha categoría.");
    }
  };

// se exportan como se deven son variables por asi asi
module.exports={
    getMoviesAPI:getMovies,
    searchTitleAPI:searchTitle,
    getMoviesCategoryAPI:getMoviesCategory
}



