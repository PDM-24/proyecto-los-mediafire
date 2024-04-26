//prcesos para aobtener la peticion, procesarla y obtener la respuesta
const controller = {};//encargado de contener la informacion
const User = require("../models/register.model");
const httpError = require("http-errors");

controller.register=async(req,res,next)=>{
    try {
        const {
          username,
          email,
          password,
          year_nac,
          genere,
         movie_genere,
         avatar
        } = req.body;
  
        const user = await User.findOne({ $or: [{ email: email }] });
  
        if (user) {
          throw httpError(409, "Ya existe esta cuenta");
        }
  
        const newUser = new User({
        
            username:username,
            email:email,
            password: password,
            year_nac:year_nac,
            genere:genere,
           movie_genere:movie_genere,
           avatar:avatar,

        });
  
        await newUser.save();
  
        return res.status(201).json({ message: "Se ha creado correctamente tu usuario" });
      } catch (error) {
        next(error);
      }
  
      
  
  


};





module.exports = controller;



