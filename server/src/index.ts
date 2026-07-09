// libs
import express from 'express';

// types
import type ServiceResponse from './types/ServiceResponse.ts';

const app = express();

app.get('/', (req, res) => {
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

app.get('/health', (req, res) => {
	res.send('Server is running')
})

app.listen(8080, () => {
	console.log('Server is running at http://localhost:8080');
});