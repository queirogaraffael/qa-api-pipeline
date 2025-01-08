import { BaseChecks, ENDPOINTS, BaseRest} from '../../support/base/baseTest.js';

export class MoviesService extends BaseRest {
    constructor(environment) {
        super(environment);
        this.checks = new BaseChecks();
    }

    createMovie(movieData, maxResponseTime, headers) {
        const response = this.post(ENDPOINTS.MOVIES, movieData, headers, null);
    
        this.checks.checkStatusCode(response, 201, 'POST /movies resposta tem status 201');
        this.checks.checkResponseTime(response, maxResponseTime, 'POST /movies tempo de resposta está dentro do limite');
        this.checks.checkResponseBodyContains(response, 'id', 'POST /movies corpo da resposta contém ID do filme');
    
        return { id: JSON.parse(response.body).id, title: movieData.title };
    }
    
    getMovies(page = 1, limit = 10, maxResponseTime, headers) {

        const queryParams = `?page=${page}&limit=${limit}`;

        const response = this.get(ENDPOINTS.MOVIES, headers, queryParams);
    
        this.checks.checkStatusCode(response, 200, 'GET /movies resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'GET /movies tempo de resposta é <= maxResponseTime');
    
        return response.json();
    }
    
    getMovieById(movieId, maxResponseTime, headers) {
        const response = this.get(`${ENDPOINTS.MOVIES}/${movieId}`, headers, null);
    
        this.checks.checkStatusCode(response, 200, 'GET /movies/{id} resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'GET /movies/{id} tempo de resposta é <= maxResponseTime');
    
        return response.json();
    }

    updateMovie(movieId, updatedData, maxResponseTime, headers) {
        const response = this.put(`${ENDPOINTS.MOVIES}/${movieId}`, updatedData, headers, null);
    
        this.checks.checkStatusCode(response, 200, 'PUT /movies resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'PUT /movies tempo de resposta é <= maxResponseTime');
    
        return response;
    }

    deleteMovie(movieId, maxResponseTime, headers) {
        const response = this.delete(`${ENDPOINTS.MOVIES}/${movieId}`, headers, null);

        this.checks.checkStatusCode(response, 200, 'DELETE /movies resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'DELETE /movies tempo de resposta é <= maxResponseTime');

        return response;
    }
}
