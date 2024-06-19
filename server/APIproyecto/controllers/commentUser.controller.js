const controller = {};//encargado de contener la informacion
const commentUser = require("../models/commentUser.model");
const httpError = require("http-errors");
const User = require('../models/account.model');

controller.postComment = async (req, res, next) => {
  try {
      const userId = req.user._id;
      const { movieId, commentText, parentId } = req.body;

      const user = await User.findById(userId);
      if (!user) {
          throw httpError(404, 'Usuario no encontrado');
      }

      if (parentId) {
          // Crear una respuesta a un comentario existente
          const parentComment = await commentUser.findById(parentId);
          if (!parentComment) {
              throw httpError(404, 'Comentario padre no encontrado');
          }

          const newReply = new commentUser({
              movieId: movieId,
              userId: userId,
              commentText: commentText,
              parentId: parentId
              //parentId: parentId || null // Establecer el parentId para la respuesta
          });

          await newReply.save();

          res.status(201).json({ message: 'Respuesta agregada exitosamente' });
      } else {
          // Crear un comentario principal
          const newComment = new commentUser({
              movieId: movieId,
              userId: userId,
              commentText: commentText
          });

          await newComment.save();

          res.status(201).json({ message: 'Comentario agregado exitosamente' });
      }
  } catch (error) {
      next(error);
  }
};


  

  
 // Controlador
controller.getComments = async (req, res, next) => {    
    try {
        const { id } = req.params; // Cambia 'movieId' por 'id' para que coincida con la ruta
        const { parentId } = req.query; // Obtén `parentId` de los parámetros de consulta (query)
        let commentsQuery = { movieId: id, parentId: null }; // Filtra por `movieId` y `parentId` nulo por defecto

        if (parentId) {
            // Si `parentId` está presente, ajusta la consulta para incluirlo
            commentsQuery = { movieId: id, parentId: parentId };
        }

        const comments = await commentUser.find(commentsQuery).populate('userId', 'username avatar');
        res.status(200).json(comments);
    } catch (error) {
        next(error);
    }
};




  module.exports = controller;