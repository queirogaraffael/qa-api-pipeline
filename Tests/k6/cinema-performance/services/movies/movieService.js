import { BaseChecks, ENDPOINTS, BaseRest } from '../../support/base/baseTest.js';

export class MoviesService extends BaseRest {
    constructor(environment) {
        super(environment);
        this.checks = new BaseChecks();
    }

    createMovie(movieData, headers) {
        return this.post(ENDPOINTS.MOVIES, movieData, headers, null);
    }

    getMovies(page = 1, limit = 20, headers) {
        const queryParams = `?page=${page}&limit=${limit}`;
        return this.get(ENDPOINTS.MOVIES, headers, queryParams);
    }

    getMoviesAll(headers) {
        return this.get(ENDPOINTS.MOVIES, headers);
    }
    
    getMovieById(movieId, headers) {
        return this.get(`${ENDPOINTS.MOVIES}/${movieId}`, headers, null);
    }

    updateMovie(movieId, updatedData, headers) {
        return this.put(`${ENDPOINTS.MOVIES}/${movieId}`, updatedData, headers, null);
    }

    deleteMovie(movieId, headers) {
        return this.delete(`${ENDPOINTS.MOVIES}/${movieId}`, headers, null);
    }
}
