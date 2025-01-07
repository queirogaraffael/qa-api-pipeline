import http from 'k6/http';
import { check } from 'k6';

export const BASE_URL = 'http://localhost:3000';

export function createMovie(maxResponseTime, headers) {
    const uniqueTitle = `Test Movie ${__VU}`;
    const launchdate = new Date().toISOString(); // Data atual
    const showtime = new Date(Date.now() + 3600000).toISOString(); // 1 hora no futuro

    const movieData = {
        title: uniqueTitle,
        description: 'A test movie for performance testing.',
        launchdate: launchdate,
        showtimes: [showtime]
    };

    const response = http.post(`${BASE_URL}/movies`, JSON.stringify(movieData), {
        headers: { ...headers, 'Content-Type': 'application/json' },
    });

    check(response, {
        'POST /movies response has status 201': (r) => r.status === 201,
        'POST /movies response time is within limit': (r) => r.timings.duration <= maxResponseTime,
        'POST /movies response body contains movie ID': (r) => JSON.parse(r.body).id !== undefined,
    });

    const movieId = JSON.parse(response.body).id; // Extrai o ID do filme criado
    return { id: movieId, title: uniqueTitle };
}


export function getMovies(maxResponseTime, headers) {
    let allMovies = [];
    let page = 1;
    let hasNextPage = true;
    let limit = 10;

    while (hasNextPage) {
        const response = http.get(`${BASE_URL}/movies?page=${page}&limit=${limit}`, {
            headers: { ...headers, 'Content-Type': 'application/json' },
        });

        check(response, {
            'GET /movies response has status 200': (r) => r.status === 200,
            'GET /movies response time is <= ${maxResponseTime}ms': (r) => r.timings.duration <= maxResponseTime,
        });

        const data = response.json();

        // Verifica se 'data' existe e contém um array de filmes
        if (Array.isArray(data.data)) {
            allMovies = allMovies.concat(data.data); // Concatena os filmes da página atual

            // Verifique se há mais páginas
            hasNextPage = data.page < data.lastPage; // A próxima página existe se a página atual for menor que a última
        } else {
            console.error('Resposta da API não contém um array de filmes em "data".');
            hasNextPage = false;  // Para o loop se a resposta não for válida
        }

        page++;
    }

    return allMovies;
}





/*
export function getMovies(maxResponseTime, headers) {
    const response = http.get(`${BASE_URL}/movies`, {
        headers: { ...headers, 'Content-Type': 'application/json' },
    });

    check(response, {
        'GET /movies response has status 200': (r) => r.status === 200,
        'GET /movies response time is <= ${maxResponseTime}ms': (r) => r.timings.duration <= maxResponseTime,
    });

    return response.json(); // Retorna os filmes como um array de objetos
}*/

export function putMovie(movieId, maxResponseTime, headers) {
    const updatedData = {
        title: `Updated Test Movie ${__VU}`,
        description: 'This is an updated description for the test movie.',
        launchdate: new Date().toISOString(),
        showtimes: [new Date(Date.now() + 7200000).toISOString()] // 2 horas no futuro
    };

    const response = http.put(`${BASE_URL}/movies/${movieId}`, JSON.stringify(updatedData), {
        headers: { ...headers, 'Content-Type': 'application/json' },
    });

    check(response, {
        'PUT /movies/${movieId} response has status 200': (r) => r.status === 200,
        'PUT /movies/${movieId} response time is <= ${maxResponseTime}ms': (r) => r.timings.duration <= maxResponseTime,
    });

    return response;
}

export function deleteMovie(movieId, maxResponseTime, headers) {
    const response = http.del(`${BASE_URL}/movies/${movieId}`, null, {
        headers: { ...headers, 'Content-Type': 'application/json' },
    });

    check(response, {
        'DELETE /movies/${movieId} response has status 200': (r) => r.status === 200,
        'DELETE /movies/${movieId} response time is <= ${maxResponseTime}ms': (r) => r.timings.duration <= maxResponseTime,
    });

    return response;
}
