import type { NextFunction, Request, Response } from "express";
import ServiceResponse from "../types/ServiceResponse.ts";
import AuthService from "../services/AuthService.ts";
import DbConnection from "../connections/DbConnection.ts";
import type { RowDataPacket } from "mysql2";
import type { UserRegistrationData } from "../types/UserRegistrationData.ts";

class AuthController {
	async register(req: Request, res: Response, next: NextFunction) : Promise<void> {
		const name: string = req.body.name;
		const email: string = req.body.email;
		const phone: string = req.body.phone;
		const username: string = req.body.username;
		const password: string = req.body.password;

		// HERE COMES THE BARRAGE OF IF STATEMENTS
		if (!name) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing name parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		if (!email) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing email parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		if (!phone) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing phone parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		if (!username) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing username parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		if (!password) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 400,
			response.payload = {
				message: 'Missing password parameter'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		const emailResults: RowDataPacket[] = (await DbConnection.pool.query<RowDataPacket[]>('SELECT user_id FROM `User` WHERE user_email = ?', [email]))[0];
		if (emailResults.length) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 409,
			response.payload = {
				message: 'A user registered with that email already exists'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		const phoneResults: RowDataPacket[] = (await DbConnection.pool.query<RowDataPacket[]>('SELECT user_id FROM `User` WHERE user_phone = ?', [phone]))[0];
		if (phoneResults.length) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 409,
			response.payload = {
				message: 'A user registered with that phone number already exists'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		const usernameResults: RowDataPacket[] = (await DbConnection.pool.query<RowDataPacket[]>('SELECT user_id FROM `User` WHERE user_username = ?', [username]))[0];
		if (usernameResults.length) {
			const response: ServiceResponse = new ServiceResponse;
			response.success = false,
			response.statusCode = 409,
			response.payload = {
				message: 'A user registered with that username already exists'
			}

			return void res.status(response.statusCode).json(response.get());
		}

		const userRegistrationData: UserRegistrationData = {
			name,
			email,
			phone,
			username,
			passwordPlainText: password
		}
		const response: ServiceResponse = await AuthService.register(userRegistrationData);
		return void res.status(response.statusCode).json(response.get());
	}
}

export default new AuthController();