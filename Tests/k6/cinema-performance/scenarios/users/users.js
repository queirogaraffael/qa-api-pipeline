import http from 'k6/http';
import { check } from 'k6';
import { BASE_URL } from './config.js';

// Função para realizar login e obter o token
export function login(email, password) {
    const payload = JSON.stringify({ email, password });

    const response = http.post(`${BASE_URL}/login`, payload, {
        headers: { 'Content-Type': 'application/json' },
    });

    check(response, {
        'Login realizado com sucesso': (r) => r.status === 200,
        'Token recebido': (r) => !!r.json('access_token'),
    });

    if (response.status === 200) {
        const body = JSON.parse(response.body);
        return body.access_token; // Retorna apenas o token
    } else {
        console.error(`Erro ao autenticar ${email}: ${response.status} - ${response.body}`);
        return null;
    }
}

// Função para carregar massa de dados
export function loadUserData() {
    return JSON.parse(open('./data/users.json'));
}
