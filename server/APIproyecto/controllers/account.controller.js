//prcesos para aobtener la peticion, procesarla y obtener la respuesta
const controller = {};//encargado de contener la informacion
const User = require("../models/account.model");
const httpError = require("http-errors");
const { createToken, verifyToken } = require("../utils/jwl.tools");

controller.register=async(req,res,next)=>{
    try {
        const {
          username,
          email,
          password,
          year_nac,
          genere,
         movie_genere,
         avatar,
         role
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
           role: role// Aquí se asegura de establecer el rol proporcionado

        });
  
        await newUser.save();
  
        return res.status(201).json({ message: "Se ha creado correctamente tu usuario" });
      } catch (error) {
        next(error);
      }
  
      
  
  


};



    //LOGIN
    controller.login = async (req, res, next) => {
      try {
        const { email,password}= req.body;

        // obteniendo informacion(correo,contraseña)
     
        const user = await User.findOne({ $or: [{email:email}] });


        if (!user) {
          throw httpError(404, "El usuario no se ha encontrado");
        }

        //verificar contraseña si no coincide
        if (!user.comparePassword(password)) {
          throw httpError(401, "contraseña incorrecta");
        }
      //   ///Exisite y ya esta verificado

      const token = await createToken(user._id);

      //   //almacenar tokens
        let _tokens = [...user.tokens];
        //verifia la integridad de los tokens actuales
        const _verifiyPromise = _tokens.map(async (_t) => {
          const status = await verifyToken(_t);
          return status ? _t : null;
        });

        //5 sesionnes
        _tokens = (await Promise.all(_verifiyPromise))
          .filter(_t => _t)
          .slice(0, 4);

        //primera posicion(shitf)
        _tokens = [token, ..._tokens];
        user.tokens = _tokens;

        await user.save();

        return res.status(200).json({
          message: 'Se ha iniciado sesión correctamente',
          token,
          role: user.role

      });
      } catch (error) {
        next(error);
      }
    };



    // LOGOUT
controller.logout = async (req, res, next) => {
  try {
    const token = req.headers.authorization.split(" ")[1];
    const { userId } = await verifyToken(token);

    const user = await User.findById(userId);

    if (!user) {
      throw httpError(404, "Usuario no encontrado");
    }

    // Eliminar el token de la lista de tokens del usuario
    user.tokens = user.tokens.filter(t => t !== token);

    await user.save();

    return res.status(200).json({
      message: 'Se ha cerrado sesión correctamente'
    });
  } catch (error) {
    next(error);
  }
};



   


module.exports = controller;