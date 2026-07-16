import type { Request, Response, NextFunction } from 'express';
import ServiceResponse from '../types/ServiceResponse.ts';

class ValidatorMiddleware {
	validateMethod(methods: string[]): (req: Request, res: Response, next: NextFunction) => void {
		return (req, res, next) => {
			if (!methods.includes(req.method)) {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 405,
				response.payload = {
					message: `${req.method} method is not allowed or implemented`
				};

				return void res.status(response.statusCode)
					.set("Allow", methods.join(','))
					.json(response.get());
			}
			next();
		}
	}
}

export default new ValidatorMiddleware();