import { ServiceResponse } from "../types/ServiceResponse.ts";
import type { RowDataPacket } from "mysql2";
import DbConnection from "../connections/DbConnection.ts";
import { generateDatabaseErrorResponse } from "../helper/generateDatabaseErrorResponse.ts";
import { generate200Response } from "../helper/generate200Response.ts";

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

interface HeroData {
	id: string,
	name: string,
	imageUrl: string | null,
	factionCode: string,
	factionName: string,
	hp: number,
	epithet: string,
	quote: string,
	hasTradeoff: boolean,
	skills: HeroSkill[]
}

interface HeroDataFilters {
	heroId?: string[],
	heroName?: string[],
	heroImageId?: string[],
	factionCode?: string[],
	heroHp?: number[],
	heroEpithet?: string[], 
	heroQuote?: string[],
	heroHasTradeoff?: boolean[],
	heroComplexity?: number[],
}

async function queryHeroById(heroId: string) {
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
		`, [heroId]);
}

async function queryHeroByName(heroName: string) {
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
		WHERE hero_name = ?;
		`, [heroName]);
}

async function queryHeroAll(filters: HeroDataFilters = {}) {
	let sql  = `
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
			WHERE 1=1
		`;
		const params: any[] = [];
		// define allowed filters and match them with the actual database column names
		const allowedFilters: Record<keyof HeroDataFilters, string> = {
			heroId: "h.hero_id",
			heroName: "h.hero_name",
			heroImageId: "h.hero_image_id",
			factionCode: "hf.hero_faction_code",
			heroHp: "h.hero_hp",
			heroEpithet: "h.hero_epithet",
			heroQuote: "h.hero_quote",
			heroHasTradeoff: "h.hero_has_tradeoff",
			heroComplexity: "h.hero_complexity"
		};
		// check each filter and add it to the query if it is allowed
		for (const [key, val] of Object.entries(filters)) {
			const col = allowedFilters[key as keyof HeroDataFilters];
			// check val and col exist
			if (val !== undefined && val !== null && col) {
				// if val is array
				if (Array.isArray(val)) {
					// check if it's empty, if it is, skip it
					if (val.length === 0) continue;
					// else check using IN clause
					else {
					sql += ` AND ${col} IN (${val.map(() => '?').join(',')})`;
					params.push(...val);
					}
				// if val is not array (although it should be, but just in case)
				} else {
					sql += ` AND ${col} = ?`;
					params.push(val);
				}
			}
		}

	return await DbConnection.pool.query<RowDataPacket[]>(sql, params);
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

	/**
	 * Service function for <code>/api/heroes/all></code>. Get all data for a hero based on custom filters. Supports <code>QUERY</code> requests.
	 * @param {HeroDataFilters} filters - the filters to apply to the query. All filters are optional. If no filters are provided, all heroes will be returned. The filters are as follows:
	 * - heroId: string[] - filter by hero ID
	 * - heroName: string[] - filter by hero name
	 * - heroImageId: string[] - filter by hero image ID
	 * - factionCode: string[] - filter by hero faction code
	 * - heroHp: number[] - filter by hero HP
	 * - heroEpithet: string[] - filter by hero epithet
	 * - heroQuote: string[] - filter by hero quote
	 * - heroHasTradeoff: boolean[] - filter by whether the hero has a tradeoff or not
	 * - heroComplexity: number[] - filter by hero complexity (1-3)
	 * @returns {Promise<ServiceResponse>}
	 * 
	 * @example <caption>cURL</caption>
	 * curl -X QUERY \
	 * -H 'Authorization: your_session_token_goes_here' \
	 * -H 'Content-Type: application/json' \
	 * -d '{"heroName": ["Từ Hoảng"], "factionCode": ["WEI", "QUN"], "heroHp": [2, 3]}' \
	 * http://localhost:8080/api/heroes/all
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
	 * - `401 UNAUTHORIZED` - No session token is provided
	 * - `405 METHOD_NOT_ALLOWED` - The endpoint does not support the HTTP method specified
	 * - `500 INTERNAL_SERVER_ERROR` - Internal server error (cooked)
	 */
	public async getHeroAll(filters: HeroDataFilters = {}): Promise<ServiceResponse> {
		let results: RowDataPacket[];
		try {
			[results] = await queryHeroAll(filters);
		} catch (err) {
			return generateDatabaseErrorResponse(err);
		}

		const data: HeroData[] = await parseDatabaseDataToReturnable(results);
		return generate200Response(data);
	}
}

const heroService = new HeroService();
export default heroService;