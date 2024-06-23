const Mongoose = require('mongoose');
const Schema = Mongoose.Schema;

const movieSchema = Schema({
  title: {
    type: String,
    required: true,
  },
  posterMovie: {
    type: String,
    required: true,
  },
  durationMovie: {
    type: String,
    required: true,
  },
  yearMovie: {
    type: String,
    required: true,
  },
  genereMovie: {
    type: String,
    required: true,
  },
  descriptionMovie: {
    type: String,
    required: true,
  },
  trailerMovie: {
    type: String,
    required: true,
  }
}, { timestamps: true });

const Movie = Mongoose.model('Movie', movieSchema);
module.exports = Movie;
