import { RowDataPacket, type ResultSetHeader } from 'mysql2';
import type { UserRegistrationData } from "../types/UserRegistrationData.ts";
import DbConnection from '../connections/DbConnection.ts';
import randomstring from 'randomstring'

async function generateUniqueUserID(): Promise<string> {
	let userIDAlreadyExists: boolean = false;
	let userID: string;

	do {
		userIDAlreadyExists = false;
		userID = randomstring.generate({
			length = 8,
			charset: 'numeric'
		});

		const [results] = await DbConnection.pool.query<RowDataPacket[]>(`SELECT user_id FROM User WHERE user_id = ?`, [userID]);
		userIDAlreadyExists = !!results.length;
	} while (userID.startsWith('0') || userIDAlreadyExists);

	return userID;
}

export class AuthService {
	/**
	 * @hidden
	 */
	constructor() {
	}

	public async register(data: UserRegistrationData) {
		let results: ResultSetHeader[];
		let userID: string = await generateUniqueUserID();

		try {

			[results] = await DbConnection.pool.execute<ResultSetHeader[]>(``)
		}
	}
}

const authService = new AuthService();
export default authService;