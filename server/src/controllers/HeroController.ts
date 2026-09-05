import type { NextFunction, Request, Response } from 'express';
import { ServiceResponse } from '../types/ServiceResponse.ts';
import HeroService from '../services/HeroService.ts';

class HeroController {
	async getHeroById(req: Request, res: Response, next: NextFunction) : Promise<void> {
		let heroId: string;
		if (Array.isArray(req.params.heroId)) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 400;
			response.payload = {
				message: "Expected heroId as string, was given array"
			};
			return void res.status(response.statusCode).json(response.get());
		} else {
			heroId = req.params.heroId;
		}

		// if no table param is used
		if (!heroId) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing hero parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		const response: ServiceResponse = await HeroService.getHeroById(heroId);
		return void res.status(response.statusCode).json(response.get());
	}

	async getHeroByName(req: Request, res: Response, next: NextFunction) : Promise<void> {
		let heroName: string;
		if (Array.isArray(req.params.heroName)) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 400;
			response.payload = {
				message: "Expected heroName as string, was given array"
			};
			return void res.status(response.statusCode).json(response.get());
		} else {
			heroName = req.params.heroName;
		}

		// if no table param is used
		if (!heroName) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing hero parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		const response: ServiceResponse = await HeroService.getHeroByName(heroName);
		return void res.status(response.statusCode).json(response.get());
	}

	async getHeroAll(req: Request, res: Response, next: NextFunction) : Promise<void> {
		const filters = req.body;
		const response: ServiceResponse = await HeroService.getHeroAll(filters);
	}
	
	async saveHero(req: Request, res: Response, next: NextFunction): Promise<void> {
		let userId: string;
		if (Array.isArray(req.params.userId)) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 400;
			response.payload = {
				message: "Expected userId as string, was given array"
			};
			return void res.status(response.statusCode).json(response.get());
		} else {
			userId = req.params.userId;
		}

		if (!userId) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing user parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		let heroId: string;
		if (Array.isArray(req.params.heroId)) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 400;
			response.payload = {
				message: "Expected heroId as string, was given array"
			};
			return void res.status(response.statusCode).json(response.get());
		} else {
			heroId = req.params.heroId;
		}

		if (!heroId) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing hero parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		const response: ServiceResponse = await HeroService.saveHero(userId, heroId);
		return void res.status(response.statusCode).json(response.get());
	}

	async getSavedHeroes(req: Request, res: Response, next: NextFunction): Promise<void> {
		let userId: string;
		if (Array.isArray(req.params.userId)) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 400;
			response.payload = {
				message: "Expected userId as string, was given array"
			};
			return void res.status(response.statusCode).json(response.get());
		} else {
			userId = req.params.userId;
		}

		if (!userId) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing user parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		const response: ServiceResponse = await HeroService.getSavedHeroes(userId);
		return void res.status(response.statusCode).json(response.get());
	}

	async unsaveHero(req: Request, res: Response, next: NextFunction): Promise<void> {
		let userId: string;
		if (Array.isArray(req.params.userId)) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 400;
			response.payload = {
				message: "Expected userId as string, was given array"
			};
			return void res.status(response.statusCode).json(response.get());
		} else {
			userId = req.params.userId;
		}

		if (!userId) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing user parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		let heroId: string;
		if (Array.isArray(req.params.heroId)) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 400;
			response.payload = {
				message: "Expected heroId as string, was given array"
			};
			return void res.status(response.statusCode).json(response.get());
		} else {
			heroId = req.params.heroId;
		}

		if (!heroId) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing hero parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		const response: ServiceResponse = await HeroService.unsaveHero(userId, heroId);
		return void res.status(response.statusCode).json(response.get());
	}
}

export default new HeroController();
