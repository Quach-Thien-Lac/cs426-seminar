import type { NextFunction, Request, Response} from 'express';
import { ServiceResponse } from '../types/ServiceResponse.ts';
import UserService from '../services/UserService.ts';

class UserController {
    async getUser(req: Request, res: Response, next: NextFunction) : Promise<void> {
        let userId: string;
        if (Array.isArray(req.params.id)) {
            const response: ServiceResponse = new ServiceResponse;
            response.success = false;
            response.statusCode = 400;
            response.payload = {
                message: "Expected userId as string, was given array"
            };
            return void res.status(response.statusCode).json(response.get());
        } else {
            userId = req.params.id;
        }

        // if no table param is used
        if (!userId) {
            const response: ServiceResponse = new ServiceResponse;
            response.success = false,
            response.statusCode = 400,
            response.payload = {
                message: 'Missing user parameter'
            }

            return void res.status(response.statusCode).json(response.get());
        }

        const response: ServiceResponse = await UserService.getUser(userId);
        return void res.status(response.statusCode).json(response.get());
    }
}

export default new UserController();