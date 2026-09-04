// libs
import express from 'express';
import bodyParser from 'body-parser';
import morgan from 'morgan';

// modules
import config from './config/config.ts';
import DbConnection from './connections/DbConnection.ts';

// routes
import AuthRoute from './routes/AuthRoute.ts';
import HeroRoute from './routes/HeroRoute.ts';
import UserRoute from './routes/UserRoute.ts';

// callbacks
import errorHandler from './middleware/errorHandler.ts';
import { rootCallback } from './middleware/rootCallback.ts';
import { healthCallback } from './middleware/healthCallback.ts';
import { nonexistentRouteCallback } from './middleware/nonexistentRouteCallback.ts';


//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
const app = express();
app.use(bodyParser.json());
app.use(morgan('dev'));

app.use('/api/auth', AuthRoute);
app.use('/api/heroes', HeroRoute);
app.use('/api', UserRoute);
app.get('/health', healthCallback);
app.get('/', rootCallback);
app.use(nonexistentRouteCallback);
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