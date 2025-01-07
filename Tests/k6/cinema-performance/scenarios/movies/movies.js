import { BaseChecks, ENDPOINTS, BaseRest } from '../../support/base/baseTest.js';

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
        this.checks.checkResponseTime(response, maxResponseTime, 'POST /movies response time is within limit');
        this.checks.checkResponseBodyContains(response, 'id', 'POST /movies response body contains movie ID');

        return { id: JSON.parse(response.body).id, title: uniqueTitle };
    }

    getMovies(maxResponseTime, headers) {
        const response = this.get('', headers);

        this.checks.checkStatusCode(response, 200, 'GET /movies response has status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'GET /movies response time is <= maxResponseTime');

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
        this.checks.checkResponseTime(response, maxResponseTime, 'PUT /movies response time is <= maxResponseTime');

        return response;
    }

    deleteMovie(movieId, maxResponseTime, headers) {
        const response = this.delete(`/${movieId}`, headers);

        this.checks.checkStatusCode(response, 200, 'DELETE /movies response has status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'DELETE /movies response time is <= maxResponseTime');

        return response;
    }
}
