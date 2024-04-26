const Mongoose = require("mongoose");
const SchemaUsers = Mongoose.Schema;
 const crypto = require("crypto");
 const debug = require("debug")("app:user-model");


//practicamente los campos de la tabla
const register_account = SchemaUsers(
    {
        
        username:{
            type:String,
            required:true,
       
        },
        email:{
            type: String,
      required: true,
      trim: true,
      unique: true,
      //no tenga diferencia entre mayuscula y minuscula
      lowercase: true,

                },
        password:{
            type:String,
            required:true,
          
        },

        year_nac:{
            type:String,
            required:true,
          
                },
       
                genere:{
                    type:String,
                  required:true
                },
                
        movie_genere:{
            type:String,
            required:true,
          
                },
        avatar:{
            type:String,
            required:true,
        
                },
                salt:{
                    type:String,
                }
    },
    { timestamps: true }
  );



  register_account.methods = {
    encryptPassword: function (pass) {
      if (!pass) return "";
      //en este try se ecnripta la contraseña
      try {
        //se necesita inmediatamente la contraseña
        const _password = crypto.pbkdf2Sync(
            //saca del documento de la salt
            pass,
            this.salt,
            1000,64, //longitud de la llaves
            `sha512`
          ).toString("hex");
  
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
    comparePassword: function (pass) {
      return this.password === this.encryptPassword(pass);
    },
  };
  
  //es temporal
  register_account.virtual("pass").set(function (
    pass = crypto.randomBytes(16).toString()
  ) {
    this.salt = this.makeSalt();
    this.password = this.encryptPassword(pass);
  });
  









  const User = Mongoose.model("User",register_account);
module.exports = User;
