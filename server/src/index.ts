// libs
import express from 'express';
import prettyMilliseconds from 'pretty-ms';
import bodyParser from 'body-parser';
import { ServiceResponse } from './types/ServiceResponse.ts';
import type { Request, Response, NextFunction } from 'express';


// modules
import config from './config/config.ts';
import DbConnection from './connections/DbConnection.ts';


// express app
const app = express();
app.use(bodyParser.json());


// routes
import AuthRoute from './routes/AuthRoute.ts';
import DbRoute from './routes/DbRoute.ts';
import errorHandler from './middleware/errorHandler.ts';

app.use('/api/auth', AuthRoute);
app.use('/api/db', DbRoute);

// health endpoint
app.get('/health', async (req: Request, res: Response) => {
	let dbPing;
	let connection;
	try {
		connection = await DbConnection.pool.getConnection();
		await connection.ping();
		dbPing = true;
	} catch (err) {
		dbPing = false;
	} finally {
		connection?.release();
	}

	const response: ServiceResponse = new ServiceResponse;
	response.success = true,
	response.statusCode = 200,
	response.payload = {
		message: 'OK',
		data: {
			uptime: prettyMilliseconds(process.uptime() * 1000),
			dbActive: dbPing
		}
	}

	res.status(response.statusCode).json(response.get());
});

// root endpoint
app.get('/', (req: Request, res: Response, next: NextFunction) => {
	const response: ServiceResponse = new ServiceResponse;
	response.success = true,
	response.statusCode = 200,
	response.payload = {
		message: 'Sanguosha Baike API root endpoint',
		data: null
	};

	res.status(response.statusCode).json(response.get());
});

app.use(errorHandler);


// port magic
const server = app.listen(config.server.port, () => {
	console.log(`Server is running at http://localhost:${config.server.port}`);
});


// graceful shutdown
async function shutdown() {
	server.close(async (err) => {
		if (err) {
			console.log(`Graceful shutdown failed. The server is fucked`);
		}

		await DbConnection.pool.end();
	});
}

process.on('SIGTERM', async () => {
	console.log('Server ending now. Good night');
	await shutdown();
});
process.on('SIGINT', async () => {
	console.log('Server ending now. Good night');
	await shutdown();
});