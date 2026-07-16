import { Router } from "express";
import DbController from "../controllers/DbController.ts";
import ValidatorMiddleware from "../middleware/ValidatorMiddleware.ts";

const router: Router = Router();

router.all('/dump',
	ValidatorMiddleware.validateMethod(['QUERY']),
	DbController.dumpTable);

export default router;