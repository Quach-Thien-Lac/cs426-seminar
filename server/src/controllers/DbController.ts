import type { NextFunction, Request, Response } from 'express';
import ServiceResponse from '../types/ServiceResponse.ts';
import DbService from '../services/DbService.ts';

class DbController {
	async getHero(req: Request, res: Response, next: NextFunction) : Promise<void> {
		const hero: string = req.body.hero;
		
		// if no table param is used
		if (!hero) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing hero parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		const response: ServiceResponse = await DbService.getHero(hero);
		return void res.status(response.statusCode).json(response.get());
	}
}

export default new DbController();