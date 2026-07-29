import { Router } from "express";
import HeroController from "../controllers/HeroController.ts";
import ValidatorMiddleware from "../middleware/ValidatorMiddleware.ts";
import { HTTPMethod } from "../enums/HTTPMethod.ts";

const router: Router = Router();

router.all('/id/:heroId',
	ValidatorMiddleware.validateMethod([HTTPMethod.GET]),
	ValidatorMiddleware.validateAccessToken,
	HeroController.getHeroById
);

router.all('/name/:heroName',
	ValidatorMiddleware.validateMethod([HTTPMethod.GET]),
	ValidatorMiddleware.validateAccessToken,
	HeroController.getHeroByName
);

export default router;