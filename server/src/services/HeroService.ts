import { ServiceResponse } from "../types/ServiceResponse.ts";
import type { ResultSetHeader, RowDataPacket } from "mysql2";
import DbConnection from "../connections/DbConnection.ts";
import { generateDatabaseErrorResponse } from "../helper/generateDatabaseErrorResponse.ts";
import { generate200Response } from "../helper/generate200Response.ts";
import { generate201Response } from "../helper/generate201Response.ts";

interface HeroSkillTag {
	skillTagCode: string,
	skillTagName: string
}

interface HeroSkill {
	skillId: string,
	skillTags: HeroSkillTag[],
	skillName: string,
	skillDescription: string
}

interface HeroFaction {
	factionCode: string,
	factionName: string
}

interface HeroData {
	id: string,
	name: string,
	imageUrl: string | null,
	factions: HeroFaction[],
	hp: number,
	epithet: string,
	quote: string,
	hasTradeoff: boolean,
	skills: HeroSkill[]
}

async function queryHeroById(heroId: string) {
	return await DbConnection.pool.query<RowDataPacket[]>(`
		SELECT
			h.hero_id,
			h.hero_name,
			h.hero_image_id,
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
			LEFT JOIN HeroSkill hs1 ON hs1.skill_id = h.hero_skill_1_id
			LEFT JOIN HeroSkill hs2 ON hs2.skill_id = h.hero_skill_2_id
			LEFT JOIN HeroSkill hs3 ON hs3.skill_id = h.hero_skill_3_id
		WHERE hero_id = ?;
		`, [heroId]);
}

async function queryHeroByName(heroName: string) {
	return await DbConnection.pool.query<RowDataPacket[]>(`
		SELECT
			h.hero_id,
			h.hero_name,
			h.hero_image_id,
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
			LEFT JOIN HeroSkill hs1 ON hs1.skill_id = h.hero_skill_1_id
			LEFT JOIN HeroSkill hs2 ON hs2.skill_id = h.hero_skill_2_id
			LEFT JOIN HeroSkill hs3 ON hs3.skill_id = h.hero_skill_3_id
		WHERE hero_name = ?;
		`, [heroName]);
}

async function querySavedHeroesByUserId(userId: string) {
	return await DbConnection.pool.query<RowDataPacket[]>(`
		SELECT
			h.hero_id,
			h.hero_name,
			h.hero_image_id,
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
		FROM HeroSaves hs
			INNER JOIN Hero h ON h.hero_id = hs.hero_id
			LEFT JOIN HeroSkill hs1 ON hs1.skill_id = h.hero_skill_1_id
			LEFT JOIN HeroSkill hs2 ON hs2.skill_id = h.hero_skill_2_id
			LEFT JOIN HeroSkill hs3 ON hs3.skill_id = h.hero_skill_3_id
		WHERE hs.user_id = ?
		ORDER BY h.hero_id;
	`, [userId]);
}

async function queryFactions(heroId: string) {
	return await DbConnection.pool.query<RowDataPacket[]>(`
		SELECT
			hf.hero_faction_code,
			hf.hero_faction_name
		FROM HeroBelongsFaction hbf
			INNER JOIN HeroFaction hf ON hf.hero_faction_id = hbf.hero_faction_id
		WHERE hbf.hero_id = ?
	`, [heroId]);
}

async function querySkillTag(skillId: string) {
	return await DbConnection.pool.query<RowDataPacket[]>(`
		SELECT
			st.skill_tag_code,
			st.skill_tag_name
		FROM HeroSkillTag hst
			INNER JOIN SkillTag st ON st.skill_tag_id = hst.skill_tag_id
		WHERE hst.skill_id = ?
	`, [skillId]);
}

async function parseDatabaseDataToReturnable(results: RowDataPacket[]) {
	return await Promise.all(results.map(async (result: RowDataPacket) => {
		const parsedResult: HeroData = {
			id: result.hero_id,
			name: result.hero_name,
			imageUrl: null, // TODO: fix later
			factions: [],
			hp: result.hero_hp,
			epithet: result.hero_epithet,
			quote: result.hero_quote,
			hasTradeoff: result.hero_has_tradeoff,
			skills: []
		}
		
		// gets the factions list
		const [heroFactionsResults] = await queryFactions(parsedResult.id);
		for (const faction of heroFactionsResults) {
			parsedResult.factions.push({
				factionCode: faction.hero_faction_code,
				factionName: faction.hero_faction_name
			});
		}

		// gets the skill list
		for (let i = 1; i <= 3; i++) {
			if (!result[`hero_skill_${i}_id`]) continue;
			let skillTags: HeroSkillTag[] = [];

			const [skillTagsResults] = await querySkillTag(result[`hero_skill_${i}_id`]);
			for (const skillTagResult of skillTagsResults) {
				skillTags.push({
					skillTagCode: skillTagResult.skill_tag_code,
					skillTagName: skillTagResult.skill_tag_name
				});
			}

			parsedResult.skills.push({
				skillId: result[`hero_skill_${i}_id`],
				skillTags,
				skillName: result[`hero_skill_${i}_name`],
				skillDescription: result[`hero_skill_${i}_description`]
			});
		}

		return parsedResult;
	}));
}

export class HeroService {
	/**
	 * @hidden
	 */
	constructor() {}

	
	/**
	 * Service function for <code>/api/heroes/id/:heroId</code>. Get all data for a hero given a hero ID. Supports <code>GET</code> requests.
	 * @param {string} heroId - The hero's ID
	 * @returns {Promise<ServiceResponse>}
	 * 
	 * @example <caption>cURL</caption>
	 * curl -X GET \
	* -H 'Authorization: your_session_token_goes_here' \
	 * http://localhost:8080/api/heroes/id/WEI015
	 * 
	 * @example <caption>Response</caption>
	 * {
	 * 	 "success": true,
	 *   "statusCode": 200,
	 *   "payload": {
	 *     "message": "OK (OK)",
	 *     "data": [
	 *       {
	 *         "id": "WEI015",
	 *         "name": "Từ Hoảng",
	 *         "imageUrl": null,
	 *         "factions": [
	 *           {
	 *             "factionCode": "WEI",
	 *             "factionName": "Nguỵ"
	 *           }
	 *         ],
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
	 * 	 }
	 * }
	 * 
	 * @response
	 * - `200 OK` - Successful request
	 * - `400 BAD_REQUEST` - Missing any of the required parameters
	 * - `401 UNAUTHORIZED` - No session token is provided
	 * - `405 METHOD_NOT_ALLOWED` - The endpoint does not support the HTTP method specified
	 * - `500 INTERNAL_SERVER_ERROR` - Internal server error (cooked)
	 */
	public async getHeroById(heroId: string): Promise<ServiceResponse> {
		let results: RowDataPacket[];

		try {
			[results] = await queryHeroById(heroId);
		} catch (err) {
			return generateDatabaseErrorResponse(err);
		}
		
		const data: HeroData[] = await parseDatabaseDataToReturnable(results);
		return generate200Response(data);
	}

	/**
	 * Service function for <code>/api/heroes/name/:heroName</code>. Get all data for a hero given hero name. Supports <code>GET</code> requests.
	 * @param {string} heroName - The hero's name
	 * @returns {Promise<ServiceResponse>}
	 * 
	 * @example <caption>cURL</caption>
	 * curl -X GET \
	 * -H 'Authorization: your_session_token_goes_here' \
	 * http://localhost:8080/api/heroes/name/T%E1%BB%AB%20Ho%E1%BA%A3ng
	 * 
	 * @example <caption>Response</caption>
	 * {
	 * 	 "success": true,
	 *   "statusCode": 200,
	 *   "payload": {
	 *     "message": "OK (OK)",
	 *     "data": [
	 *       {
	 *         "id": "WEI015",
	 *         "name": "Từ Hoảng",
	 *         "imageUrl": null,
	 *         "factions": [
	 *           {
	 *             "factionCode": "WEI",
	 *             "factionName": "Nguỵ"
	 *           }
	 *         ],
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
	 * 	 }
	 * }
	 * 
	 * @response
	 * - `200 OK` - Successful request
	 * - `400 BAD_REQUEST` - Missing any of the required parameters
	 * - `401 UNAUTHORIZED` - No session token is provided
	 * - `405 METHOD_NOT_ALLOWED` - The endpoint does not support the HTTP method specified
	 * - `500 INTERNAL_SERVER_ERROR` - Internal server error (cooked)
	 */
	public async getHeroByName(heroName: string): Promise<ServiceResponse> {
		let results: RowDataPacket[];
		try {
			[results] = await queryHeroByName(heroName);
		} catch (err) {
			return generateDatabaseErrorResponse(err);
		}

		const data: HeroData[] = await parseDatabaseDataToReturnable(results);
		return generate200Response(data);
	}

	public async saveHero(userId: string, heroId: string): Promise<ServiceResponse> {
		let userResults: RowDataPacket[];
		let heroResults: RowDataPacket[];
		let savedResults: RowDataPacket[];

		try {
			[userResults] = await DbConnection.pool.query<RowDataPacket[]>(`
				SELECT user_id FROM User WHERE user_id = ?;
			`, [userId]);
			[heroResults] = await DbConnection.pool.query<RowDataPacket[]>(`
				SELECT hero_id FROM Hero WHERE hero_id = ?;
			`, [heroId]);
			[savedResults] = await DbConnection.pool.query<RowDataPacket[]>(`
				SELECT user_id, hero_id FROM HeroSaves WHERE user_id = ? AND hero_id = ?;
			`, [userId, heroId]);
		} catch (err) {
			return generateDatabaseErrorResponse(err);
		}

		if (!userResults.length) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 404;
			response.payload = {
				message: 'User not found'
			};
			return response;
		}

		if (!heroResults.length) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 404;
			response.payload = {
				message: 'Hero not found'
			};
			return response;
		}

		if (savedResults.length) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 409;
			response.payload = {
				message: 'Hero already saved'
			};
			return response;
		}

		try {
			await DbConnection.pool.execute<ResultSetHeader>(`
				INSERT INTO HeroSaves (user_id, hero_id) VALUES (?, ?);
			`, [userId, heroId]);
		} catch (err) {
			return generateDatabaseErrorResponse(err);
		}

		return generate201Response({ userId, heroId });
	}

	public async getSavedHeroes(userId: string): Promise<ServiceResponse> {
		let results: RowDataPacket[];

		try {
			[results] = await querySavedHeroesByUserId(userId);
		} catch (err) {
			return generateDatabaseErrorResponse(err);
		}

		const data: HeroData[] = await parseDatabaseDataToReturnable(results);
		return generate200Response(data);
	}

	public async unsaveHero(userId: string, heroId: string): Promise<ServiceResponse> {
		let result: ResultSetHeader;

		try {
			[result] = await DbConnection.pool.execute<ResultSetHeader>(`
				DELETE FROM HeroSaves WHERE user_id = ? AND hero_id = ?;
			`, [userId, heroId]);
		} catch (err) {
			return generateDatabaseErrorResponse(err);
		}

		if (result.affectedRows === 0) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false;
			response.statusCode = 404;
			response.payload = {
				message: 'Saved hero entry not found'
			};
			return response;
		}

		return generate200Response({ userId, heroId });
	}
}

const heroService = new HeroService();
export default heroService;
