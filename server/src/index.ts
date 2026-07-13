// libs
import express from 'express';

// types
import type ServiceResponse from './types/ServiceResponse.ts';
import type { Request, Response } from 'express';

// config
import config from './config/config.ts';

const app = express();

app.get('/', (req: Request, res: Response) => {
	const rootResponse: ServiceResponse = {
		success: true,
		statusCode: 200,
		payload: {
			message: 'Sanguosha Baike API root endpoint',
			data: null
		}
	}

	res.send(rootResponse);
});

app.get('/health', (req: Request, res: Response) => {
	res.send('Server is running');
});

app.listen(config.server.port, () => {
	console.log(`Server is running at http://localhost:${config.server.port}`);
});