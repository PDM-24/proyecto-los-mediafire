const controller = {};
const User = require('../models/account.model');
const httpError = require('http-errors');
const MoviesService = require('../services/movies.services');

controller.getUserData = async (req, res, next) => {
  try {
    const userId = req.user._id;
    const user = await User.findById(userId);
    if (!user) {
      throw httpError(404, 'Usuario no encontrado');
    }

    let moviesByGenre = {};
    if (!user.hasSeenRandomMovies) {
      const genres = user.movie_genere;
      for (const genre of genres) {
        const movies = await MoviesService.getMoviesCategoryAPI(genre, 20);
        moviesByGenre[genre] = movies;
      }
     
    }

    res.status(200).json({
      user: {
        username: user.username,
        year_nac: user.year_nac,
        genere: user.genere,
        movie_genere: user.movie_genere,
        avatar: user.avatar,

      },
      movies: moviesByGenre
    });
  } catch (error) {
    next(error);
  }
};

module.exports = controller;
