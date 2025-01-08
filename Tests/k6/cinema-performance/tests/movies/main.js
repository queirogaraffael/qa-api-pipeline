import { check } from 'k6';
import { SharedArray } from 'k6/data';
import { MoviesService } from '../../scenarios/movies/movieScenarios.js';
import { ENVIRONMENTS } from '../../support/base/constants.js';

const moviesService = new MoviesService(ENVIRONMENTS.LOCAL);

const data = new SharedArray('Movies', function () {
    const jsonData = JSON.parse(open('../../data/dynamic/movies/movies.json'));
    return jsonData.movies;
});

export const options = {
    scenarios: {
        load_test: {
            executor: 'constant-arrival-rate',
            rate: 50, // 50 requisições por segundo
            timeUnit: '1s', 
            duration: '1m', // Teste executa por 1 minuto
            preAllocatedVUs: 50,
            maxVUs: 100,
            exec: 'loadTest', // Nome da função
        },
        stress_test: {
            executor: 'ramping-arrival-rate',
            startRate: 10, // Começa com 10 requisições por segundo
            timeUnit: '1s',
            stages: [
                { target: 100, duration: '2m' }, // Escala para 100 requisições/seg em 2 minutos
                { target: 200, duration: '2m' }, // Escala para 200 requisições/seg em 2 minutos
                { target: 0, duration: '1m' }, // Finaliza reduzindo para 0 requisições
            ],
            preAllocatedVUs: 200,
            maxVUs: 300,
            exec: 'stressTest',
        },
        peak_test: {
            executor: 'per-vu-iterations',
            vus: 100,
            iterations: 1000, // Cada VU executa 1000 iterações
            maxDuration: '2m', // Tempo máximo de execução
            exec: 'peakTest',
        },
        resilience_test: {
            executor: 'shared-iterations',
            vus: 50, // 50 usuários virtuais
            iterations: 500, // 500 iterações no total
            maxDuration: '3m', // Tempo máximo de execução
            exec: 'resilienceTest',
        },
    },
};

export function loadTest() {
    executeFlow();
}

export function stressTest() {
    executeFlow();
}

export function peakTest() {
    executeFlow();
}

export function resilienceTest() {
    executeFlow();
}

function executeFlow() {
    const maxResponseTime = 500; // Tempo máximo de resposta em ms
    const headers = { 'Content-Type': 'application/json' };

    // 1. Criar um filme
    const movieData = data[__ITER % data.length]; // Seleciona um filme da massa de dados
    const createdMovie = moviesService.createMovie(movieData, maxResponseTime, headers);

    check(createdMovie, {
        'Filme criado com sucesso': () => createdMovie.id !== undefined,
    });

    // 2. Buscar o filme pelo ID
    const fetchedMovie = moviesService.getMovieById(createdMovie.id, maxResponseTime, headers);

    check(fetchedMovie, {
        'Filme buscado com sucesso pelo ID': () => fetchedMovie.id === createdMovie.id,
        'Título está correto': () => fetchedMovie.title === movieData.title,
    });

    // 3. Atualizar o filme
    const updatedData = {
        title: `${movieData.title} - Atualizado`,
        description: `${movieData.description} - Atualizado`,
        releaseYear: 2025,
    };
    const updatedResponse = moviesService.updateMovie(createdMovie.id, updatedData, maxResponseTime, headers);

    check(updatedResponse, {
        'Filme atualizado com sucesso': () => updatedResponse.status === 200,
    });

    const updatedMovie = moviesService.getMovieById(createdMovie.id, maxResponseTime, headers);
    check(updatedMovie, {
        'Título do filme atualizado corretamente': () => updatedMovie.title === updatedData.title,
        'Descrição do filme atualizada corretamente': () => updatedMovie.description === updatedData.description,
    });

    // 4. Deletar o filme
    const deleteResponse = moviesService.deleteMovie(createdMovie.id, maxResponseTime, headers);

    check(deleteResponse, {
        'Filme deletado com sucesso': () => deleteResponse.status === 200,
    });
}
