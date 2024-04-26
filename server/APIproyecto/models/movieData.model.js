const Mongoose = require("mongoose");
const SchemaUsers = Mongoose.Schema;
 const debug = require("debug")("app:user-model");



 const movieDataSchema=SchemaUsers(
    {

    posterMovie:{
        type:String
    },
    titleMovie:{
        type:String,
    },
    durationMovie:{
        type:Number
    },

    yearMovie:{
        type:Number
    },
    genereMovie:{
        type:String
    },
    descriptionMovie:{
        type:String
    },
    trailerMovie:{
        type:String 
    }    
},
 { timestamps: true }

)

const Movie = Mongoose.model("MovieData",movieDataSchema);
module.exports = Movie;
