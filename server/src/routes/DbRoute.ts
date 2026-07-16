import { Router } from "express";
import DbController from "../controllers/DbController.ts";
import ValidatorMiddleware from "../middleware/ValidatorMiddleware.ts";
import { HTTPMethod } from "../enums/HTTPMethod.ts";

const router: Router = Router();

router.all('/get-hero',
	ValidatorMiddleware.validateMethod([HTTPMethod.QUERY]),
	DbController.getHero
);

export default router;