const Mongoose = require("mongoose");
const SchemaUsers = Mongoose.Schema;

const commentSchema = SchemaUsers({
  movieId: {
    type: Number,
    required: true
  },
  userId: {
    type: Mongoose.Schema.Types.ObjectId,
    ref:'User',
    required: true
  },
  commentText: {
    type: String,
    required: true
  }
}, { timestamps: true });

const Comment = Mongoose.model('CommentUser', commentSchema);
module.exports = Comment;


  