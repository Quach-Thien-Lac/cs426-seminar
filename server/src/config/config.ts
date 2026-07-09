import 'dotenv/config'

const config: any = {
	client: {},
	server: {
		port: process.env.PORT
	},
	thirdparty: {},
	db: {}
};

export default config;