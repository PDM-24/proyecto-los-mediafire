// const { jwtVerify } = require("jose");
// const User = require('../models/account.model');
// const httpError = require('http-errors');
// const { verifyToken } = require('../utils/jwl.tools');

// const authenticate = async (req, res, next) => {
//     try {
//         const authHeader = req.headers.authorization;
//         if (!authHeader || !authHeader.startsWith('Bearer ')) {
//             throw httpError(401, 'No autorizado');
//         }

//         const token = authHeader.split(' ')[1];
//         const payload = await verifyToken(token);

//         if (!payload) {
//             throw httpError(401, 'Token inválido');
//         }

//         const user = await User.findById(payload.sub); // `sub` debería ser el ID del usuario
//         if (!user) {
//             throw httpError(401, 'Usuario no encontrado');
//         }

//         req.user = user;
//         next();
//     } catch (error) {
//         next(error);
//     }
// };

// module.exports = authenticate;

const { verifyToken } = require('../utils/jwl.tools');
const User = require('../models/account.model');
const httpError = require('http-errors');

const authenticate = async (req, res, next) => {
    try {
        const authHeader = req.headers.authorization;
        if (!authHeader || !authHeader.startsWith('Bearer ')) {
            throw httpError(401, 'No autorizado');
        }

        const token = authHeader.split(' ')[1];
        const payload = await verifyToken(token);

        if (!payload) {
            throw httpError(401, 'Token inválido');
        }

        const user = await User.findById(payload.sub); // `sub` debería ser el ID del usuario
        if (!user) {
            throw httpError(401, 'Usuario no encontrado');
        }

        req.user = user;
        next();
    } catch (error) {
        next(error);
    }
};

module.exports = authenticate;
