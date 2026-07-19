import type { RowDataPacket } from "mysql2";
import DbConnection from "../connections/DbConnection.ts";
import { ServiceResponse } from "../types/ServiceResponse.ts";
import getDatabaseErrorResponse from "../helper/getDatabaseErrorResponse.ts";



export class DbService {
	/**
	 * @hidden
	 */
	constructor() {
	}
}

const dbService = new DbService();
export default dbService;