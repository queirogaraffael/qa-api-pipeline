import { MoviesService } from '../../scenarios/movies/movies.js';
import { responseTimeConfig } from '../../support/config/movies/responseTimes.js';
import { stagesConfig } from '../../support/config/movies/config.js';
import { AuthService } from '../../scenarios/login/login.js';

const stage = __ENV.STAGE || 'stage1';
const maxResponseTimes = responseTimeConfig[stage];

export let options = {
    stages: stagesConfig[stage],
};

let authService = new AuthService();

// Carrega os dados dos usuários
const users = authService.loadUserData();

// Setup: Autentica um usuário específico
export function setup() {
    const selectedUser = users[0]; // Seleciona apenas o primeiro usuário
    const token = authService.login(selectedUser.email, selectedUser.password);

    if (!token) {
        throw new Error(`Falha ao autenticar o usuário: ${selectedUser.email}`);
    }

    return { token };
}

// Fluxo completo: Criação → Listagem → Atualização → Exclusão
export default function ({ token }) {
    const authHeaders = { Authorization: `Bearer ${token}` };
    const moviesService = new MoviesService();

    console.log('🛠️ Executando fluxo completo de filme.');

    // 🟢 Criação
    const newMovie = moviesService.createMovie(maxResponseTimes.createMovie, authHeaders);
    if (!newMovie || !newMovie.id) {
        console.error('Erro ao criar filme. ID não encontrado.');
        return;
    }
    console.log(`✅ Filme criado: ${newMovie.title} (ID: ${newMovie.id})`);

    // 🔵 Listagem
    const movies = moviesService.getMovies(maxResponseTimes.getMovies, authHeaders);
    const movie = movies.find((m) => m.title === newMovie.title);

    if (movie) {
        console.log(`🔍 Filme encontrado: ${movie.title} (ID: ${movie._id})`);

        // 🟡 Atualização
        moviesService.updateMovie(movie._id, maxResponseTimes.putMovie, authHeaders);
        console.log(`🔄 Filme atualizado: ${movie.title} (ID: ${movie._id})`);

        // 🔴 Exclusão
        moviesService.deleteMovie(movie._id, maxResponseTimes.deleteMovie, authHeaders);
        console.log(`🗑️ Filme deletado: ${movie.title} (ID: ${movie._id})`);
    } else {
        console.error(`❌ Filme "${newMovie.title}" não encontrado na listagem.`);
    }
}
