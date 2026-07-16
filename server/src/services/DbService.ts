import DbConnection from "../connections/DbConnection.ts";
import ServiceResponse from "../types/ServiceResponse.ts";

class DbService {
	async getHero(hero: string): Promise<ServiceResponse> {
		const [results] = await DbConnection.pool.execute(`SELECT * FROM Hero WHERE hero_id = ?;`, [hero]);

		const response: ServiceResponse = new ServiceResponse;
		response.success = true,
		response.statusCode = 200,
		response.payload = {
			message: 'OK',
			data: results
		};

		return response;
	}
}

export default new DbService();