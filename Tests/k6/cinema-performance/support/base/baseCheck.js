import { check } from 'k6';

export class BaseChecks {

    checkStatusCode(response, expectedStatus = 200, message = `Status esperado ${expectedStatus}`) {
        check(response, {
            [message]: (r) => r && r.status === expectedStatus,
        });
    }

    checkResponseCreated(response, message = 'Status da resposta é 201 (Criado)') {
        this.checkStatusCode(response, 201, message);
    }

    checkResponseDeleted(response, message = 'Status da resposta é 200 (Deletado)') {
        this.checkStatusCode(response, 200, message);
    }

    checkLoginSuccess(response, message = 'Login bem-sucedido com status 200') {
        this.checkStatusCode(response, 200, message);
    }
}
