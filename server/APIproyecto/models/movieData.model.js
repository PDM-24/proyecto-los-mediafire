const Mongoose = require('mongoose');
const Schema = Mongoose.Schema;

const movieSchema = Schema({

  title: {
    type: String,
    required: true
},
synopsis: {
    type: String,
    required: true
},
duration: {
    type: String,
    required: true
},
actors: [{
    name: String,
    profileUrl: String
}],
coverPhoto: {
    type: String,
    required: true
},
categories: [{
    type: String,
    required: true
}]
 
}, { timestamps: true });

const Movie = Mongoose.model('Movie', movieSchema);
module.exports = Movie;







