import type { RowDataPacket, ResultSetHeader } from 'mysql2';
import type { UserRegistrationData } from "../types/UserRegistrationData.ts";
import DbConnection from '../connections/DbConnection.ts';
import randomstring from 'randomstring'
import bcrypt from 'bcrypt';
import { ServiceResponse } from '../types/ServiceResponse.ts';

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

function generateSessionToken(): string {
	const sessionToken = randomstring.generate({
		length: 60,
		charset: ['hex']
	});
	return sessionToken
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

	/**
	 * Service function for <code>/api/auth/register</code>. Registers a new user. The newly created user ID is included in the payload data, as well as the <code>Location</code> header of the response. Supports <code>POST</code> requests.
	 * @param {UserRegistrationData} data - User registration's payload data.
	 * @returns {Promise<ServiceResponse>}
	 * 
	 * @example <caption>cURL</caption>
	 * curl -X POST \
	 * --header 'Content-Type:application/json' \
	 * --data '{"name": "Hatsune Miku", "email": "mikuuwu@gmail.com", "phone": "0393676939", "username": "therealhatsunemiku", "password": "supersecuremikumikupassword"}' \
	 * http://localhost:8080/api/auth/register
	 * 
	 * @example <caption>Response</caption>
	 * {
	 *   "success": true,
	 *   "statusCode": 201,
	 *   "payload": {
	 *     "message": "OK (CREATED)",
	 *     "data": {
	 *       "userID": "56892408",
	 *       "username": "therealhatsunemiku"
	 *     }
	 *   }
	 * }
	 * 
	 * @response
	 * - `201 CREATED` - Successful request
	 * - `400 BAD_REQUEST` - Missing any of the required parameters
	 * - `405 METHOD_NOT_ALLOWED` - The endpoint does not support the HTTP method specified
	 * - `409 CONFLICT` - Requested user already has some of the parameters in the database
	 * - `500 INTERNAL_SERVER_ERROR` - Internal server error (cooked)
	 */
	public async register(data: UserRegistrationData): Promise<ServiceResponse> {
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
			// insert user
			await DbConnection.pool.execute<ResultSetHeader[]>(`
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
				userID: userID,
				username: data.username
			}
		}
		return response;
	}

	public async login(username: string, password: string): Promise<ServiceResponse> {
		// WELCOME TO HOW TO LOGIN 101
		// first...we check if the username exist!!!11!
		const userWithGivenUsername: RowDataPacket[] = (await DbConnection.pool.query<RowDataPacket[]>('SELECT * FROM `User` WHERE user_username = ?', [username]))[0];
		if (!userWithGivenUsername.length) {
			const response = new ServiceResponse;
			response.success = false;
			response.statusCode = 404;
			response.payload = {
				message: 'Username not found'
			};

			return response;
		}

		// THENNNNNNN we check password hashhhhhhh
		const passwordHash = userWithGivenUsername[0]['user_password_hash'];
		const passwordMatch = await bcrypt.compare(password, passwordHash);
		if (!passwordMatch) {
			const response = new ServiceResponse;
			response.success = false;
			response.statusCode = 401;
			response.payload = {
				message: 'Wrong password'
			};

			return response;
		}
		
		// finnalllLLYLLLLYYYY wee make session token and return it.
		try {
			const sessionToken = generateSessionToken();
			await DbConnection.pool.execute<ResultSetHeader[]>(`
				DELETE FROM Session WHERE session_user_id = ?;	
			`, [
				userWithGivenUsername[0]['user_id']
			]);	
			await DbConnection.pool.execute<ResultSetHeader[]>(`
				INSERT INTO Session (session_token, session_user_id, session_creation_time) VALUES
				(?, ?, ?);
			`, [
				sessionToken,
				userWithGivenUsername[0]['user_id'],
				dateToSQLDatetime(new Date(Date.now()))
			]);
			
			const response = new ServiceResponse;
			response.success = true;
			response.statusCode = 201;
			response.payload = {
				message: 'OK',
				data: {
					userID: userWithGivenUsername[0]['user_id'],
					sessionToken: sessionToken
				}
			};
			return response;
		} catch (err) {
			if (err instanceof Error) {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 500;
				response.payload = {
					message: 'The database is cooked while trying to insert a session token',
					data: err.toString()
				};
				return response;
			} else {
				const response: ServiceResponse = new ServiceResponse;
				response.success = false;
				response.statusCode = 500;
				response.payload = {
					message: 'dude what the god DAMN ERROR??????? IS NOPT AN ERROR?????????????????????????'
				};
				return response;
			}
		}
	}
}

const authService = new AuthService();
export default authService;