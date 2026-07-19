import { Router } from "express";
import HeroController from "../controllers/HeroController.ts";
import ValidatorMiddleware from "../middleware/ValidatorMiddleware.ts";
import { HTTPMethod } from "../enums/HTTPMethod.ts";

const router: Router = Router();

router.all('/:heroId',
	ValidatorMiddleware.validateMethod([HTTPMethod.QUERY]),
	ValidatorMiddleware.validateAccessToken,
	HeroController.getHero
);

export default router;