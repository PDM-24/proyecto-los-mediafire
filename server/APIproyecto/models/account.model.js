const Mongoose = require("mongoose");
const SchemaUsers = Mongoose.Schema;
const crypto = require("crypto");
const debug = require("debug")("app:user-model");

//practicamente los campos de la tabla
const accountSchema = SchemaUsers(
  {
    username: {
      type: String,
      required: true,
      trim: true,
      unique: true,
      //no tenga diferencia entre mayuscula y minuscula
      lowercase: true,
    },
    email: {
      type: String,
      required: true,
      trim: true,
      unique: true,
      //no tenga diferencia entre mayuscula y minuscula
      lowercase: true,
    },
    hashedPassword: {
      type: String,
      required: true,
    },

    year_nac: {
      type: String,
      required: true,
    },

    genere: {
      type: String,
      required: true,
    },

    movie_genere: {
      type: Array,
      required: true,
    },
    avatar: {
      type: String,
      required: true,
    },
    salt: {
      type: String,
    },
    tokens: {
      type: [String],
      default: [],
    },
  },
  { timestamps: true }
);

accountSchema.methods = {
  encryptPassword: function (password) {
    if (!password) return "";
    //en este try se ecnripta la contraseña
    try {
      //se necesita inmediatamente la contraseña
      const _password = crypto
        .pbkdf2Sync(
          //saca del documento de la salt
          password,
          this.salt,
          1000,
          64, //longitud de la llaves
          `sha512`
        )
        .toString("hex");

      return _password;
    } catch (error) {
      debug({ error });
      return "";
    }
  },
  //proceso aleatorio , usa generacion random mas avanzado
  makeSalt: function () {
    //cantidad de caracteres
    return crypto.randomBytes(16).toString("hex");
  },
  //se compara con la contraseña que se escriba con el encriptado
  comparePassword: function (password) {
    return this.hashedPassword === this.encryptPassword(password);
  },
};

//es temporal
accountSchema
  .virtual("password")
  .set(function (password = crypto.randomBytes(16).toString()) {
    this.salt = this.makeSalt();
    this.hashedPassword = this.encryptPassword(password);
  });

const User = Mongoose.model("User", accountSchema);
module.exports = User;
