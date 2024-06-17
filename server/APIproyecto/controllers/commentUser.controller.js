const controller = {};//encargado de contener la informacion
const commentUser = require("../models/commentUser.model");
const httpError = require("http-errors");
const User = require('../models/account.model');

controller.postComment = async (req, res, next) => {
    try {

  // Obtener el usuario autenticado desde req.user (gracias al middleware authenticate)
  const userId = req.user._id;

  // Buscar al usuario en la base de datos
  const user = await User.findById(userId);
  if (!user) {
      throw httpError(404, 'Usuario no encontrado');
  }

      const { movieId, commentText } = req.body;
  
      const newComment = new commentUser({
        movieId:movieId,
        userId:userId,
        commentText:commentText
      });
  
      await newComment.save();
  
      res.status(201).json({ message: 'Comentario agregado exitosamente' });
    } catch (error) {
      next(error);
    }
  };

  

  
 // Controlador
controller.getComments = async (req, res, next) => {    
    try {
        const { id } = req.params; // Cambia 'movieId' por 'id' para que coincida con la ruta
        const comments = await commentUser.find({ movieId: id }).populate('userId', 'username avatar');
        res.status(200).json(comments);
    } catch (error) {
        next(error);
    }
};




  module.exports = controller;