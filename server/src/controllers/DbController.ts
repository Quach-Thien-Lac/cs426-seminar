import type { NextFunction, Request, Response } from 'express';
import ServiceResponse from '../types/ServiceResponse.ts';
import DbService from '../services/DbService.ts';

class DbController {
	async dumpTable(req: Request, res: Response, next: NextFunction) {
		const table = req.query.table;
		
		// if no table param is used
		if (!table) {
			const response: ServiceResponse = {
				success: false,
				statusCode: 400,
				payload: {
					message: 'Missing table parameter'
				}
			};

			res.sendStatus(response.statusCode).send(response);
		}

		const response: ServiceResponse = await DbService.dumpTable(table);
		res.sendStatus(response.statusCode).send(response);
	}
}

export default new DbController()