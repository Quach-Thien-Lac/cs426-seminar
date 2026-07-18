import type { RowDataPacket } from "mysql2";
import DbConnection from "../connections/DbConnection.ts";
import { ServiceResponse } from "../types/ServiceResponse.ts";
import getDatabaseErrorResponse from "../helper/getDatabaseErrorResponse.ts";

async function queryHero(hero: string) {
	return await DbConnection.pool.query<RowDataPacket[]>(`
		SELECT
			h.hero_id,
			h.hero_name,
			h.hero_image_id,
			hf.hero_faction_code,
			hf.hero_faction_name,
			h.hero_hp,
			h.hero_epithet,
			h.hero_quote,
			h.hero_has_tradeoff,
			hs1.skill_id AS hero_skill_1_id,
			hs1.skill_name AS hero_skill_1_name,
			hs1.skill_description AS hero_skill_1_description,
			hs2.skill_id AS hero_skill_2_id,
			hs2.skill_name AS hero_skill_2_name,
			hs2.skill_description AS hero_skill_2_description,			
			hs3.skill_id AS hero_skill_3_id,
			hs3.skill_name AS hero_skill_3_name,
			hs3.skill_description AS hero_skill_3_description
		FROM Hero h
			INNER JOIN HeroFaction hf ON hf.hero_faction_id = h.hero_faction_id
			
			LEFT JOIN HeroSkill hs1 ON hs1.skill_id = h.hero_skill_1_id
			LEFT JOIN HeroSkill hs2 ON hs2.skill_id = h.hero_skill_2_id
			LEFT JOIN HeroSkill hs3 ON hs3.skill_id = h.hero_skill_3_id
		WHERE hero_id = ?;
		`, [hero]);
}

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
	 * curl -X QUERY \
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
	 *         "id": "WEI015",
	 *         "name": "Từ Hoảng",
	 *         "imageUrl": null,
	 *         "factionCode": "WEI",
	 *         "factionName": "Nguỵ",
	 *         "hp": 2,
	 *         "epithet": "Chu Á Chi Phong",
	 *         "quote": "Thanh Đông kích Tây, thiêu kỳ lương thảo!",
	 *         "hasTradeoff": false,
	 *         "skills": [
	 *           {
	 *             "skillId": "WEI015_1",
	 *             "skillTags": [],
	 *             "skillName": "Đoạn Lương",
	 *             "skillDescription": "Giai đoạn hành động, bạn có thể sử dụng thẻ bài Phi Cẩm Nang sắc Đen xem như 1 thẻ [Binh Lương Thốn Đoạn] không hạn chế khoảng cách. Nếu bạn vừa sử dụng thẻ [Binh Lương Thốn Đoạn] đối với 1 người chới trong khoảng cách vượt quá 2, bạn không thể tái phát động kỹ năng cho đến hết lượt."
	 *           }
	 *         ]
	 *       }
	 *     ]
	 *   }
	 * }
	 * 
	 * @response
	 * - `200 OK` - Successful request
	 * - `400 BAD_REQUEST` - Missing any of the required parameters
	 * - `405 METHOD_NOT_ALLOWED` - The endpoint does not support the HTTP method specified
	 * - `500 INTERNAL_SERVER_ERROR` - Internal server error (cooked)
	 */
	public async getHero(hero: string): Promise<ServiceResponse> {
		let results: RowDataPacket[];

		try {
			[results] = await queryHero(hero);
		} catch (err) {
			return getDatabaseErrorResponse(err);
		}

		
		const data = await Promise.all(results.map(async (result: RowDataPacket) => {
			const parsedResult: any = {
				id: result.hero_id,
				name: result.hero_name,
				imageUrl: null, // TODO: fix later
				factionCode: result.hero_faction_code,
				factionName: result.hero_faction_name,
				hp: result.hero_hp,
				epithet: result.hero_epithet,
				quote: result.hero_quote,
				hasTradeoff: result.hero_has_tradeoff,
				skills: []
			}

			for (let i = 1; i <= 3; i++) {
				if (!result[`hero_skill_${i}_id`]) continue;

				const [skillTagsResults] = await DbConnection.pool.query<RowDataPacket[]>(`
					SELECT
						st.skill_tag_code,
						st.skill_tag_name
					FROM HeroSkillTag hst
						INNER JOIN SkillTag st ON st.skill_tag_id = hst.skill_tag_id
					WHERE hst.skill_id = ?
				`, [result[`hero_skill_${i}_id`]]);
				parsedResult.skills.push({
					skillId: result[`hero_skill_${i}_id`],
					skillTags: skillTagsResults,
					skillName: result[`hero_skill_${i}_name`],
					skillDescription: result[`hero_skill_${i}_description`]
				});
			}

			return parsedResult;
		}));

		const response: ServiceResponse = new ServiceResponse;
		response.success = true,
		response.statusCode = 200,
		response.payload = {
			message: 'OK',
			data
		};

		return response;
	}
}

const dbService = new DbService();
export default dbService;