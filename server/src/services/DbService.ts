import DbConnection from "../connections/DbConnection.ts";
import ServiceResponse from "../types/ServiceResponse.ts";

class DbService {
	async dumpTable(table: String) {
		const [results, fields] = await DbConnection.pool.query('SELECT * FROM ?', [table]);

		const response: ServiceResponse = {
			success: true,
			statusCode: 200,
			payload: {
				message: 'OK',
				data: results
			}
		}

		return response;
	}
}

export default new DbService();