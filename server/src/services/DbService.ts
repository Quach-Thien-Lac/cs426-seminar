import DbConnection from "../connections/DbConnection.ts";
import ServiceResponse from "../types/ServiceResponse.ts";

class DbService {
	/**
	 * Service function for <code>/api/db/get-hero</code>. Get all data for a hero. Supports <code>QUERY</code> requests.
	 * @param {string} hero - The hero's ID
	 * @returns {Promise<ServiceResponse>}
	 * 
	 * @example <caption>cURL</caption>
	 * curl -X POST \
	 * --header 'Content-Type:application/json' \
	 * --data '{"hero": "WEI015"}' \
	 * http://localhost:8080/api/db/get-hero
	 * 
	 * @property {OK} 200 - Successful request
	 */
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