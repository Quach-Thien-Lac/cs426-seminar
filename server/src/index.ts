import express from 'express';

const app = express();

app.get('/', (req, res) => {
	res.send('Server is running');
});

app.get('/health', (req, res) => {
	res.send('Server is running')
})

app.listen(8080, () => {
	console.log('Server is running at http://localhost:8080');
});