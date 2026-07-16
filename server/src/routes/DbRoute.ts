import { Router } from "express";
import DbController from "../controllers/DbController.ts";
import ValidatorMiddleware from "../middleware/ValidatorMiddleware.ts";

const router: Router = Router();

router.all('/get-hero',
	ValidatorMiddleware.validateMethod(['QUERY']),
	DbController.getHero
);

export default router;