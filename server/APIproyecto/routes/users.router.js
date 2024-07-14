//Ruta de crear usuario

const express = require("express");

const router = express.Router(); //enrutador
const AccountController = require("../controllers/account.controller");
const movieController = require("../controllers/movieData.controller");

const {
  createAccountValidator,
} = require("../validators/createAccount.validator");
const validateAccount = require("../middlewares/index.middlewares");
const {
  loginAccountValidator,
} = require("../validators/loginAccount.validator");

const authorization = require("../middlewares/authorization.middlewares");
const authenticate = require("../middlewares/authorization.middlewares");
const userLoginController = require('../controllers/userLogin.controller');
const notificationController =require('../controllers/commentUser.controller')
//api/account/register
router.post(
  "/register",
  //el validador va primero
  createAccountValidator,
  validateAccount,
  AccountController.register
);
router.post(
  "/login",

  loginAccountValidator,
  validateAccount,
  AccountController.login,
  authorization
);
router.post('/logout', authenticate, AccountController.logout);

router.get('/user/Home',authenticate,userLoginController.getUserData)


router.get('/user/notifications', authenticate, notificationController.getNotifications);
router.patch('/user/notifications/:id', authenticate, notificationController.markAsRead);

//CREAR PELICULAS

//crear pelicula
router.post('/user/admin/home/movies', movieController.movieData);

//borrar peli

router.delete('/user/admin/home/movies/:id', movieController.deleteMovie);  



//todas las peliculas creadas por id
router.get('/user/admin/home/:id',authenticate, movieController.getMovieByAdminId);

//buscar actor
router.get('/user/admin/home/movies/actors/search/:actorName', authenticate, movieController.searchActorsByName);


//traer peliculas creadas
router.get('/user/admin/home', movieController.getAllMovies);



module.exports = router;