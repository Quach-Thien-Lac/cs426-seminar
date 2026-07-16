import type { Request, Response, NextFunction } from 'express';
import type ServiceResponse from '../types/ServiceResponse.ts';

class ValidatorMiddleware {
	validateMethod(methods: string[]): (req: Request, res: Response, next: NextFunction) => void {
		return (req, res, next) => {
			if (!methods.includes(req.method)) {
				const response: ServiceResponse = {
					success: false,
					statusCode: 405,
					payload: {
						message: `${req.method} method is not allowed or implemented`
					}
				};

				return void res.status(response.statusCode)
					.set("Allow", methods.join(','))
					.json(response);
			}
			next();
		}
	}
}

export default new ValidatorMiddleware();