import { RowDataPacket, type ResultSetHeader } from 'mysql2';
import type { UserRegistrationData } from "../types/UserRegistrationData.ts";
import DbConnection from '../connections/DbConnection.ts';
import randomstring from 'randomstring'
import bcrypt from 'bcrypt';
import ServiceResponse from '../types/ServiceResponse.ts';

const SALT_ROUNDS: number = 10;

async function generateUniqueUserID(): Promise<string> {
	let userIDAlreadyExists: boolean = false;
	let userID: string;

	do {
		userIDAlreadyExists = false;
		userID = randomstring.generate({
			length: 8,
			charset: 'numeric'
		});

		const [results] = await DbConnection.pool.query<RowDataPacket[]>(`SELECT user_id FROM User WHERE user_id = ?`, [userID]);
		userIDAlreadyExists = !!results.length;
	} while (userID.startsWith('0') || userIDAlreadyExists);

	return userID;
}

function dateToSQLDatetime(date: Date) {
	return date.toISOString().slice(0, 19).replace('T', ' ');
}

export class AuthService {
	/**
	 * @hidden
	 */
	constructor() {
	}

	public async register(data: UserRegistrationData): Promise<ServiceResponse> {
		let results: ResultSetHeader[];
		const userID: string = await generateUniqueUserID();
		const passwordHash: string = await bcrypt.hash(data.passwordPlainText, SALT_ROUNDS);

		let activeUserStatusID: number;
		try {
			activeUserStatusID = (await DbConnection.pool.query<RowDataPacket[]>(`
				SELECT user_status_id FROM UserStatus WHERE user_status_code = 'ACTIVE';		
			`))[0][0].user_status_id;
		} catch (err) {
			if (err instanceof Error) {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 500;
				response.payload = {
					message: 'The database is cooked while trying to find the given active user status ID',
					data: err.toString()
				};
				return response;
			} else {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 500;
				response.payload = {
					message: 'What the fuck bro even the ERROR is COOKOEKDKD!??????????????'
				};
				return response;
			}
		}

		let viewerUserRoleID: number;
		try {
			viewerUserRoleID = (await DbConnection.pool.query<RowDataPacket[]>(`
				SELECT user_role_id FROM UserRole WHERE user_role_code = 'VIEWER';		
			`))[0][0].user_role_id;
		} catch (err) {
			if (err instanceof Error) {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 500;
				response.payload = {
					message: 'The database is cooked while trying to find the given viewer user role ID',
					data: err.toString()
				};
				return response;
			} else {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 500;
				response.payload = {
					message: '...fym the error is NOT AN ERROR????????????????????????'
				};
				return response;
			}
		}

		try {
			[results] = await DbConnection.pool.execute<ResultSetHeader[]>(`
				INSERT INTO User (user_id, user_name, user_email, user_phone, user_username, user_password_hash, user_registration_time, user_status_id, user_role_id)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
			`, [
				userID,
				data.name,
				data.email,
				data.phone,
				data.username,
				passwordHash,
				dateToSQLDatetime(new Date(Date.now())),
				activeUserStatusID,
				viewerUserRoleID
			]);
		} catch (err) {
			if (err instanceof Error) {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 500;
				response.payload = {
					message: 'The database is cooked while trying to insert user',
					data: err.toString()
				};
				return response;
			} else {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 500;
				response.payload = {
					message: 'i am NOT DEALING WITH *checks error* A ERROR THAT IS NOT AN ERROR?????????'
				};
				return response;
			}
		}

		const response: ServiceResponse = new ServiceResponse;
		response.success = true;
		response.statusCode = 201;
		response.payload = {
			message: "OK",
			data: {
				userID: userID
			}
		}
		return response;
	}
}

const authService = new AuthService();
export default authService;