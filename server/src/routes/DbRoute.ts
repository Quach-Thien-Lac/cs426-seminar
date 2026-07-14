import { Router } from "express";
import DbController from "../controllers/DbController.ts";

const router: Router = Router();

router.post('/dump', DbController.dumpTable);

export default router;