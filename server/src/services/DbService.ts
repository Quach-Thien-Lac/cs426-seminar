import DbConnection from "../connections/DbConnection.ts";
import config from '../config/config.ts';
import type ServiceResponse from "../types/ServiceResponse.ts";

class DbService {
	async dumpTable(table: string): Promise<ServiceResponse> {
		const [results, fields] = await DbConnection.pool.query(`SELECT * FROM ${table};`);
		console.log(results[0].hero_name);

		const response: ServiceResponse = {
			success: true,
			statusCode: 200,
			payload: {
				message: 'OK',
				data: results
			}
		};

		return response;
	}
}

export default new DbService();