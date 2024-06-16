const controller = {};//encargado de contener la informacion

const User = require('../models/account.model');
const httpError = require('http-errors');
const MoviesService = require('../services/movies.services');

controller.getUserData = async (req, res, next) => {
    try {
        // Obtener el usuario autenticado desde req.user (gracias al middleware authenticate)
        const userId = req.user._id; // Suponiendo que el ID del usuario está en req.user

        // Buscar al usuario en la base de datos
        const user = await User.findById(userId);
        if (!user) {
            throw httpError(404, 'Usuario no encontrado');
        }

        // Obtener películas por cada género favorito del usuario (limitado a las primeras 10)
        const genres = user.movie_genere;
        const moviesByGenre = {};

        for (const genre of genres) {
            const movies = await MoviesService.getMoviesCategoryAPI(genre,10 ); // Obtener las primeras 10 películas
            moviesByGenre[genre] = movies;
        }

        // Devolver datos del usuario junto con las películas obtenidas por género
        res.status(200).json({
            user: {
                username: user.username,
                year_nac: user.year_nac,
                genere: user.genere,
                movie_genere: user.movie_genere,
                avatar: user.avatar
            },
            movies: moviesByGenre
        });
    } catch (error) {
        next(error);
    }
};
module.exports = controller;