import type { RowDataPacket, ResultSetHeader } from 'mysql2';
import type { UserRegistrationData } from "../types/UserRegistrationData.ts";
import DbConnection from '../connections/DbConnection.ts';
import randomstring from 'randomstring'
import bcrypt from 'bcrypt';
import { ServiceResponse } from '../types/ServiceResponse.ts';

export class UserService {
    /**
	 * @hidden
	 */
	constructor() {
	}

	/**
	 * Service function for <code>/api/users/id. Get user data given a user ID. 
	 * @param {string} id - The user's ID
	 * @returns {Promise<ServiceResponse>}
	 * 
	 * @example <caption>cURL</caption>
	 * curl -X QUERY \
	 * --header 'Content-Type:application/json' \
	 * --data '' \
	 * http://localhost:8080/api/user/id
	 * 
	 * @example <caption>Response</caption>
	 * {
	 *   "success": true,
	 *   "statusCode": 200,
	 *   "payload": {
	 *     "message": "OK (OK)",
	 *     "data": [
	 *       "user_id": "dd0d1234",
     *        "user_name": "dd0d",
     *       "user_username": "dd0d",
     *       "user_email": "dd0d@gmail.com",
     *      "user_phone": "1234567890",
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

    async getUser(id: string): Promise<ServiceResponse> {
        let results: RowDataPacket[];
        try {
            [results] = await DbConnection.pool.query<RowDataPacket[]>('SELECT user_id, user_name, user_username, user_email, user_phone FROM `User` WHERE user_id = ?', [id]);
        } catch (error) {
            if (error instanceof Error) {
                const response: ServiceResponse = new ServiceResponse;
                response.success = false;
                response.statusCode = 500;
                response.payload.message = `Internal server error: ${error.message}`;
                return response;
            } else {
                const response: ServiceResponse = new ServiceResponse;
                response.success = false;
                response.statusCode = 500;
                response.payload.message = 'Internal server error: Unknown error';
                return response;
            }
    }
        if (!results.length) {
            const response: ServiceResponse = new ServiceResponse;
            response.success = false;
            response.statusCode = 404;
            response.payload.message = 'User doesn\'t exist in database';
            return response;
        }

        const response: ServiceResponse = new ServiceResponse;
        response.success = true;
        response.statusCode = 200;
        response.payload.message = 'User found';
        response.payload.data = results[0];
        return response;

    }
}

const userService = new UserService();
export default userService;