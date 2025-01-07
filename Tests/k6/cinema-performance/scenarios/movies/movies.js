import { BaseRest } from '../../services/BaseRest.js';
import { BaseChecks } from './baseChecks.js';
import { ENDPOINTS } from '../support/config/Endpoints.js';

export class MoviesService extends BaseRest {
    constructor() {
        super(ENDPOINTS.PRODUCTS);
        this.checks = new BaseChecks();
    }

    createMovie(maxResponseTime, headers) {
        const uniqueTitle = `Test Movie ${__VU}`;
        const launchdate = new Date().toISOString();
        const showtime = new Date(Date.now() + 3600000).toISOString();

        const movieData = {
            title: uniqueTitle,
            description: 'A test movie for performance testing.',
            launchdate,
            showtimes: [showtime],
        };

        const response = this.post('', movieData, headers);

        this.checks.checkStatusCode(response, 201, 'POST /movies response has status 201');
        check(response, {
            'POST /movies response time is within limit': (r) => r.timings.duration <= maxResponseTime,
            'POST /movies response body contains movie ID': (r) => JSON.parse(r.body).id !== undefined,
        });

        return { id: JSON.parse(response.body).id, title: uniqueTitle };
    }

    getMovies(maxResponseTime, headers) {
        let allMovies = [];
        let page = 1;
        let hasNextPage = true;
        const limit = 10;

        while (hasNextPage) {
            const response = this.get(`?page=${page}&limit=${limit}`, headers);

            this.checks.checkStatusCode(response, 200, 'GET /movies response has status 200');
            check(response, {
                'GET /movies response time is <= maxResponseTime': (r) => r.timings.duration <= maxResponseTime,
            });

            const data = response.json();

            if (Array.isArray(data.data)) {
                allMovies = allMovies.concat(data.data);
                hasNextPage = data.page < data.lastPage;
            } else {
                console.error('Resposta da API não contém um array de filmes em "data".');
                hasNextPage = false;
            }

            page++;
        }

        return allMovies;
    }

    getMovies(maxResponseTime, headers) {
        const response = this.get('', headers);

        this.checks.checkStatusCode(response, 200, 'GET /movies response has status 200');
        check(response, {
            'GET /movies response time is <= maxResponseTime': (r) => r.timings.duration <= maxResponseTime,
        });

        return response.json(); 
    }

    updateMovie(movieId, maxResponseTime, headers) {
        const updatedData = {
            title: `Updated Test Movie ${__VU}`,
            description: 'This is an updated description for the test movie.',
            launchdate: new Date().toISOString(),
            showtimes: [new Date(Date.now() + 7200000).toISOString()],
        };

        const response = this.put(`/${movieId}`, updatedData, headers);

        this.checks.checkStatusCode(response, 200, 'PUT /movies response has status 200');
        check(response, {
            'PUT /movies response time is <= maxResponseTime': (r) => r.timings.duration <= maxResponseTime,
        });

        return response;
    }

    deleteMovie(movieId, maxResponseTime, headers) {
        const response = this.delete(`/${movieId}`, headers);

        this.checks.checkStatusCode(response, 200, 'DELETE /movies response has status 200');
        check(response, {
            'DELETE /movies response time is <= maxResponseTime': (r) => r.timings.duration <= maxResponseTime,
        });

        return response;
    }
}
