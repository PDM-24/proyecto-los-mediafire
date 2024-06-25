const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const commentSchema = new Schema({
    movieId: {
        type: Number,
        required: true
    },
    
    userId: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    commentText: {
        type: String,
        required: true
    },
    parentId: {
        type: Schema.Types.ObjectId,
        ref: 'CommentUser' // Referencia a otro comentario en el mismo esquema
    },
  

}, { timestamps: true });



const Comment = mongoose.model('CommentUser', commentSchema);
module.exports = Comment;
