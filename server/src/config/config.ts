import 'dotenv/config';

const config: any = {
	client: {},
	server: {
		port: process.env.PORT
	},
	thirdparty: {},
	db: {
		dbHost: process.env.DB_HOST,
		dbUser: process.env.DB_USER,
		dbPassword: process.env.DB_PASSWORD,
		dbName: process.env.DB_NAME,
		dbPort: process.env.DB_PORT
	}
};

export default config;