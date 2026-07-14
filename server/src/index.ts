// libs
import express from 'express';
import prettyMilliseconds from 'pretty-ms';
import type ServiceResponse from './types/ServiceResponse.ts';
import type { Request, Response, NextFunction } from 'express';


// modules
import config from './config/config.ts';
import DbConnection from './connections/DbConnection.ts';


// express app
const app = express();


// root endpoint
app.get('/', (req: Request, res: Response, next: NextFunction) => {
	const rootResponse: ServiceResponse = {
		success: true,
		statusCode: 200,
		payload: {
			message: 'Sanguosha Baike API root endpoint',
			data: null
		}
	};

	res.send(rootResponse);
});

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

	const healthResponse: ServiceResponse = {
		success: true,
		statusCode: 200,
		payload: {
			message: 'OK',
			data: {
				uptime: prettyMilliseconds(process.uptime() * 1000),
				dbActive: dbPing
			}
		}
	};

	res.send(healthResponse);
});


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