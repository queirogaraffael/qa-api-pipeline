import { BaseChecks, ENDPOINTS, BaseRest } from '../../support/base/baseTest.js';

export class AuthService extends BaseRest {
    constructor() {
        super(ENDPOINTS.LOGIN);
        this.checks = new BaseChecks();
    }

    login(email, password) {
        const payload = { email, password };

        const response = this.post('', payload, {
            'Content-Type': 'application/json',
        });

        this.checks.checkStatusCode(response, 200, 'POST /login response has status 200');
        check(response, {
            'Token recebido': (r) => !!r.json('access_token'),
        });

        if (response.status === 200) {
            const body = response.json();
            return body.access_token;
        } else {
            console.error(`Erro ao autenticar ${email}: ${response.status} - ${response.body}`);
            return null;
        }
    }

    loadUserData() {
        try {
            return JSON.parse(open('./data/users.json'));
        } catch (error) {
            console.error('Erro ao carregar dados de usuários:', error);
            return [];
        }
    }
}
