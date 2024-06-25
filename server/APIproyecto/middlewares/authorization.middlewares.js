
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
        // if (user.role !== 'admin') {
        //     throw httpError(403, 'Acceso denegado. Permiso de administrador requerido.');
        // }

        req.user = user;
        next();
    } catch (error) {
        next(error);
    }
};

module.exports = authenticate;
