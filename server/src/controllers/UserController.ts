import type { NextFunction, Request, Response } from 'express';
import { ServiceResponse } from '../types/ServiceResponse.ts';
import UserService from '../services/UserService.ts';

class UserController {
	async getUser(req: Request, res: Response, next: NextFunction) : Promise<void> {
		const id: string = req.body.id;

        // if no table param is used
        if (!id) {
            const response: ServiceResponse = new ServiceResponse;
            response.success = false,
            response.statusCode = 400,
            response.payload = {
                message: 'Missing id parameter'
            }
            return void res.status(response.statusCode).json(response.get());
        }

        const response: ServiceResponse = await UserService.getUser(id);
        return void res.status(response.statusCode).json(response.get());
	}
}

export default new UserController();