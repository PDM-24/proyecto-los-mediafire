const Mongoose = require('mongoose');
const Schema = Mongoose.Schema;

const movieSchema = Schema({

  title: {
    type: String,
},
synopsis: {
    type: String,
},
duration: {
    type: String,
},
actors: [{
    name: String,
    profileUrl: String
}],
coverPhoto: {
    type: String,
},
categories: [{
    type: String,
}]
 
}, { timestamps: true });

const Movie = Mongoose.model('Movie', movieSchema);
module.exports = Movie;