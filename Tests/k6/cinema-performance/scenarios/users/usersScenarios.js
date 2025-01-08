import { BaseChecks, ENDPOINTS, BaseRest } from '../../support/base/baseTest.js';

export class UsersService extends BaseRest {
    constructor(environment) {
        super(environment);
        this.checks = new BaseChecks();
    }

    createUser(userData, maxResponseTime, headers) {
        const response = this.post(ENDPOINTS.USERS, userData, headers, null);
    
        this.checks.checkStatusCode(response, 201, 'POST /users resposta tem status 201');
        this.checks.checkResponseTime(response, maxResponseTime, 'POST /users tempo de resposta está dentro do limite');
        this.checks.checkResponseBodyContains(response, 'id', 'POST /users corpo da resposta contém ID do usuário');
    
        return { id: JSON.parse(response.body).id, ...userData };
    }
    
    
    getUserById(userId, maxResponseTime, headers) {
        const response = this.get(`${ENDPOINTS.USERS}/${userId}`, headers, null);
    
        this.checks.checkStatusCode(response, 200, 'GET /users/{id} resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'GET /users/{id} tempo de resposta é <= maxResponseTime');
    
        return response.json();
    }

    getUsers(page = 1, limit = 10, maxResponseTime, headers) {
        const queryParams = `?page=${page}&limit=${limit}`;
        const response = this.get(ENDPOINTS.USERS, headers, queryParams);
    
        this.checks.checkStatusCode(response, 200, 'GET /users resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'GET /users tempo de resposta é <= maxResponseTime');
    
        return response.json();
    }

    updateUser(userId, updatedData, maxResponseTime, headers) {
        const response = this.put(`${ENDPOINTS.USERS}/${userId}`, updatedData, headers, null);
    
        this.checks.checkStatusCode(response, 200, 'PUT /users resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'PUT /users tempo de resposta é <= maxResponseTime');
    
        return response;
    }

    deleteUser(userId, maxResponseTime, headers) {
        const response = this.delete(`${ENDPOINTS.USERS}/${userId}`, headers, null);

        this.checks.checkStatusCode(response, 200, 'DELETE /users resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'DELETE /users tempo de resposta é <= maxResponseTime');

        return response;
    }
}
