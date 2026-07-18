import {Router} from "express";
import UserController from "../controllers/UserController";
import ValidatorMiddleware from "../middleware/ValidatorMiddleware.ts";
import {HTTPMethod} from "../enums/HTTPMethod.ts";

const router: Router = Router();

router.all('/user/:id',
    ValidatorMiddleware.validateMethod([HTTPMethod.GET]),
    ValidatorMiddleware.validateAccessToken,
    UserController.getUser
);

export default router;

