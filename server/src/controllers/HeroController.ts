import type { NextFunction, Request, Response } from 'express';
import { ServiceResponse } from '../types/ServiceResponse.ts';
import HeroService from '../services/HeroService.ts';

class HeroController {
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

		const response: ServiceResponse = await HeroService.getHero(hero);
		return void res.status(response.statusCode).json(response.get());
	}
}

export default new HeroController();