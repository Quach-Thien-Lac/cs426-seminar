import type { Request, Response, NextFunction } from 'express';
import { ServiceResponse } from '../types/ServiceResponse.ts';
import prettyMilliseconds from 'pretty-ms';
import DbConnection from '../connections/DbConnection.ts';


export const healthCallback = async (req: Request, res: Response, next: NextFunction) => {
	let dbPing;
	let connection;
	try {
		connection = await DbConnection.pool.getConnection();
		await connection.ping();
		dbPing = true;
	} catch (err) {
		dbPing = false;
	} finally {
		connection?.release();
	}

	const response: ServiceResponse = new ServiceResponse;
	response.success = true,
	response.statusCode = 200,
	response.payload = {
		message: 'OK',
		data: {
			uptime: prettyMilliseconds(process.uptime() * 1000),
			dbActive: dbPing
		}
	};

	return void res.status(response.statusCode).json(response.get());
}