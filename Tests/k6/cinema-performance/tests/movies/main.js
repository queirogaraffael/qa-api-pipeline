import { createMovie, getMovies, putMovie, deleteMovie } from '../../scenarios/movies.js';
import { responseTimeConfig } from '../../support/config/movies/responseTimes.js';
import { stagesConfig } from './config.js';
import { login, loadUserData } from '../../scenarios/users.js';

const stage = __ENV.STAGE || 'stage1';
const TEST_CASE = __ENV.TEST_CASE || 'default';
const maxResponseTimes = responseTimeConfig[stage];

export let options = {
    stages: stagesConfig[stage],
};

// Carrega a massa de dados de usuários no escopo global
const users = loadUserData();

// Função de setup para obter tokens
export function setup() {
    const userTokens = users.map((user) => {
        const token = login(user.email, user.password);
        if (token) {
            return { email: user.email, token };
        } else {
            console.error(`Falha ao autenticar o usuário: ${user.email}`);
            return null;
        }
    }).filter(Boolean); // Remove valores nulos ou indefinidos

    if (userTokens.length === 0) {
        throw new Error('Nenhum token foi gerado. Verifique os dados dos usuários.');
    }

    return userTokens;
}
export default function (userTokens) {
    // Seleciona um token aleatório para este Virtual User (VU)
    const userToken = userTokens[__VU % userTokens.length]?.token;

    if (!userToken) {
        console.error('Token não encontrado para o Virtual User.');
        return;
    }

    const authHeaders = { Authorization: `Bearer ${userToken}` };
    let movies = []; // Declaração fora do switch

    switch (TEST_CASE) {
        case 'create':
            createMovie(maxResponseTimes.createMovie, authHeaders); // Cria o filme
            break;

        case 'list':
            movies = getMovies(maxResponseTimes.getMovies, authHeaders); // Atribui o valor de filmes
            console.log('Movies list:', movies);  // Exibe todos os filmes carregados
            break;

        case 'update':
            putMovie('movie-id-placeholder', maxResponseTimes.putMovie, authHeaders);
            break;

        case 'delete':
            deleteMovie('movie-id-placeholder', maxResponseTimes.deleteMovie, authHeaders);
            break;

        default:
            // Fluxo padrão: criação, listagem, atualização e exclusão
            const newMovie = createMovie(maxResponseTimes.createMovie, authHeaders); // Criação do filme
            if (!newMovie || !newMovie.title) {
                console.error('Erro ao criar filme. Título não encontrado.');
                return;
            }

            movies = getMovies(maxResponseTimes.getMovies, authHeaders); // Atribui os filmes carregados
            const movie = movies.find((m) => m.title === newMovie.title); // Encontra o filme recém-criado

            if (movie) {
                // Se o filme for encontrado, realiza a atualização e exclusão
                putMovie(movie._id, maxResponseTimes.putMovie, authHeaders);
                deleteMovie(movie._id, maxResponseTimes.deleteMovie, authHeaders);
            } else {
                console.error(`Filme com título "${newMovie.title}" não encontrado na listagem.`);
            }
            break;
    }
}
