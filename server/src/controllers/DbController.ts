import type { NextFunction, Request, Response } from 'express';
import type ServiceResponse from '../types/ServiceResponse.ts';
import DbService from '../services/DbService.ts';

class DbController {
	async dumpTable(req: Request, res: Response, next: NextFunction) {
		const table: any = req.body.table;
		console.log(table);
		
		// if no table param is used
		if (!table) {
			const response: ServiceResponse = {
				success: false,
				statusCode: 400,
				payload: {
					message: 'Missing table parameter'
				}
			};

			return void res.status(response.statusCode).json(response);
		}

		const response: ServiceResponse = await DbService.dumpTable(table);
		return void res.status(response.statusCode).json(response);
	}
}

export default new DbController()