import type { FieldPacket, RowDataPacket } from "mysql2";
import DbConnection from "../connections/DbConnection.ts";
import ServiceResponse from "../types/ServiceResponse.ts";

export class DbService {
	/**
	 * @hidden
	 */
	constructor() {
	}

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
	 * @example <caption>Response</caption>
	 * {
	 *   "success": true,
	 *   "statusCode": 200,
	 *   "payload": {
	 *     "message": "OK (OK)",
	 *     "data": [
	 *       {
	 *         "hero_id": "WEI015",
	 *         "hero_name": "Từ Hoảng",
	 *         "hero_image_id": null,
	 *         "hero_faction_id": 1,
	 *         "hero_hp": 2,
	 *         "hero_epithet": "Chu Á Chi Phong",
	 *         "hero_quote": "Thanh Đông kích Tây, thiêu kỳ lương thảo!",
	 *         "hero_has_tradeoff": {
	 *           "type": "Buffer",
	 *           "data": [
	 *             0
	 *           ]
	 *         },
	 *         "hero_skill_1_id": "WEI015_1",
	 *         "hero_skill_2_id": null,
	 *         "hero_skill_3_id": null
	 *       }
	 *     ]
	 *   }
	 * }
	 * 
	 * @response
	 * - `200 OK` - Successful request
	 * - `400 BAD_REQUEST` - Missing any of the required parameters
	 * - `404 NOT_FOUND` - The specified hero does not exist in the database
	 * - `405 METHOD_NOT_ALLOWED` - The endpoint does not support the HTTP method specified
	 * - `500 INTERNAL_SERVER_ERROR` - Internal server error (cooked)
	 */
	public async getHero(hero: string): Promise<ServiceResponse> {
		let results: RowDataPacket[];

		try {
			[results] = await DbConnection.pool.query<RowDataPacket[]>(`SELECT * FROM Hero WHERE hero_id = ?;`, [hero]);
		} catch (err) {
			if (err instanceof Error) {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 500;
				response.payload = {
					message: 'The database is cooked',
					data: err.toString()
				}
				return response;
			} else {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 500;
				response.payload = {
					message: 'What the fuck bro even the ERROR is cooked??????',
				}
				return response;
			}

		}

		if (!results.length) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 404,
			response.payload = {
				message: 'Hero does not exist',
			};

			return response;
		}

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

const dbService = new DbService();
export default dbService;