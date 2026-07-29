import mysql from 'mysql2/promise';
import type { Pool } from 'mysql2/promise';
import config from '../config/config.ts';

class DbConnection {
	pool: Pool;

	constructor() {
		this.pool = mysql.createPool({
			connectionLimit: 5,
			host: 'db', // i don't know how the FUCK this resolves to the correct host
			user: config.db.dbUser,
			password: config.db.dbPassword,
			database: config.db.dbName,
			waitForConnections: true,
			port: config.db.dbPort,
			enableKeepAlive: true,
			charset: 'utf8mb4',

			typeCast: (field, useDefaultTypeCasting) => {
				if (field.type === 'BIT' && field.length === 1) {
					const bytes = field.buffer();
					return (bytes ? bytes[0] === 1 : false);
				}

				return useDefaultTypeCasting();
			}
		});
	}
}

export default new DbConnection();