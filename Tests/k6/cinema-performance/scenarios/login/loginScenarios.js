import { BaseChecks, ENDPOINTS, BaseRest } from '../../support/base/baseTest.js';

export class AuthService extends BaseRest {
    constructor() {
        super(ENDPOINTS.LOGIN);
        this.checks = new BaseChecks();
    }

    login(email, password) {
        const payload = { email, password };

        const response = this.post(ENDPOINTS.LOGIN, payload, null, null);

        this.checks.checkStatusCode(response, 200, 'POST /login response has status 200');
        this.checks.checkTokenReceived(response, 'POST /login token recebido');

        if (response.status === 200) {
            const body = response.json();
            return body.access_token;
        } else {
            console.error(`Erro ao autenticar ${email}: ${response.status} - ${response.body}`);
            return null;
        }
    }
}
