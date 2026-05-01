import { check } from 'k6';

export class BaseChecks {
    checkStatusCode(response, expectedStatus = 200, message = `Status esperado ${expectedStatus}`, tags = {}) {
        check(response, {
            [message]: (r) => r && r.status === expectedStatus,
        }, tags);
    }

    checkResponse(response, expectedStatus = 200, message = `Status esperado ${expectedStatus}`, tags = {}) {
        this.checkStatusCode(response, expectedStatus, message, tags);
    }

    checkResponseCreated(response, message = 'Status da resposta é 201 (Criado)', tags = {}) {
        this.checkStatusCode(response, 201, message, tags);
    }

    checkResponseDeleted(response, message = 'Status da resposta é 200 (Deletado)', tags = {}) {
        this.checkStatusCode(response, 200, message, tags);
    }

    checkLoginSuccess(response, message = 'Login bem-sucedido com status 200', tags = {}) {
        this.checkStatusCode(response, 200, message, tags);
    }

    checkResponseTime(response, maxResponseTime, comparisonOperator = '<', message = 'Response time is within the limit', tags = {}) {
        let checkCondition;

        if (comparisonOperator === '<') {
            checkCondition = (r) => r.timings.duration < maxResponseTime;
        } else if (comparisonOperator === '>') {
            checkCondition = (r) => r.timings.duration > maxResponseTime;
        } else if (comparisonOperator === '<=') {
            checkCondition = (r) => r.timings.duration <= maxResponseTime;
        } else if (comparisonOperator === '>=') {
            checkCondition = (r) => r.timings.duration >= maxResponseTime;
        } else if (comparisonOperator === '==') {
            checkCondition = (r) => r.timings.duration === maxResponseTime;
        } else {
            throw new Error('Invalid comparison operator');
        }

        check(response, {
            [message]: checkCondition,
        }, tags);
    }
}
