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

router.all('/save/:userId/:heroId',
	ValidatorMiddleware.validateMethod([HTTPMethod.POST]),
	ValidatorMiddleware.validateAccessToken,
	HeroController.saveHero
);

router.all('/saved/:userId',
	ValidatorMiddleware.validateMethod([HTTPMethod.GET]),
	ValidatorMiddleware.validateAccessToken,
	HeroController.getSavedHeroes
);

router.all('/unsave/:userId/:heroId',
	ValidatorMiddleware.validateMethod([HTTPMethod.DELETE]),
	ValidatorMiddleware.validateAccessToken,
	HeroController.unsaveHero
);

export default router;
