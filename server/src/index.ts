// libs
import express from 'express';
import prettyMilliseconds from 'pretty-ms';
import type ServiceResponse from './types/ServiceResponse.ts';
import type { Request, Response, NextFunction } from 'express';


// configs and db pool
import config from './config/config.ts';



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
app.get('/health', (req: Request, res: Response) => {
	const healthResponse: ServiceResponse = {
		success: true,
		statusCode: 200,
		payload: {
			message: 'OK',
			data: {
				uptime: prettyMilliseconds(process.uptime() * 1000)
			}
		}
	};

	res.send(healthResponse);
});

// port magic
app.listen(config.server.port, () => {
	console.log(`Server is running at http://localhost:${config.server.port}`);
});