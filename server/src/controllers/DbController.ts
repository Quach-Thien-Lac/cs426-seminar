import type { NextFunction, Request, Response } from 'express';
import type ServiceResponse from '../types/ServiceResponse.ts';
import DbService from '../services/DbService.ts';

class DbController {
	async getHero(req: Request, res: Response, next: NextFunction) : Promise<void> {
		const hero: any = req.body.hero;
		console.log(req.body);
		
		// if no table param is used
		if (!hero) {
			const response: ServiceResponse = {
				success: false,
				statusCode: 400,
				payload: {
					message: 'Missing hero parameter'
				}
			};

			return void res.status(response.statusCode).json(response);
		}

		const response: ServiceResponse = await DbService.getHero(hero);
		return void res.status(response.statusCode).json(response);
	}
}

export default new DbController()