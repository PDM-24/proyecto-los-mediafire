const controller = {};
const commentUser = require("../models/commentUser.model");
const httpError = require("http-errors");
const User = require('../models/account.model');

controller.postComment = async (req, res, next) => {
  try {
      const userId = req.user._id;
      const { id: movieId } = req.params; // Usar el parámetro de ruta
      const { commentText, parentId } = req.body;

      const user = await User.findById(userId);
      if (!user) {
          throw httpError(404, 'Usuario no encontrado');
      }

      if (parentId) {
          const parentComment = await commentUser.findById(parentId);
          if (!parentComment) {
              throw httpError(404, 'Comentario padre no encontrado');
          }

          const newReply = new commentUser({
              movieId,
              userId,
              commentText,
              parentId
          });

          await newReply.save();
          res.status(201).json({ message: 'Respuesta agregada exitosamente' });
      } else {
          const newComment = new commentUser({
              movieId,
              userId,
              commentText
          });

          await newComment.save();
          res.status(201).json({ message: 'Comentario agregado exitosamente' });
      }
  } catch (error) {
      next(error);
  }
};

// Función para obtener comentarios y respuestas
controller.getComments = async (req, res, next) => {
  try {
  
    const { id: movieId } = req.params;
    const { parentId } = req.query;
    let commentsQuery = { movieId, parentId: null };

    if (parentId) {
      commentsQuery = { movieId, parentId };
    }

    const comments = await commentUser.find(commentsQuery).populate('userId', 'username avatar').sort({ createdAt: -1 });
    res.status(200).json(comments);
  } catch (error) {
    next(error);
  }
};


// Función para obtener respuestas a un comentario específico
controller.getRepliesToComment = async (req, res, next) => {
    try {
      // const userId = req.user._id;

      // const user = await User.findById(userId);
      // if (!user) {
      //     throw httpError(404, 'Usuario no encontrado');
      // }
      const { id: movieId, parentId } = req.params;
  
      const replies = await commentUser.find({ movieId, parentId }).populate('userId', 'username avatar').sort({ createdAt: 1 });

      res.status(200).json(replies);
    } catch (error) {
      next(error);
    }
  };
module.exports = controller;
