import { BaseChecks, ENDPOINTS, BaseRest } from '../../support/base/baseTest.js';

export class UserService extends BaseRest {
    constructor(environment) {
        super(environment);
        this.checks = new BaseChecks();
    }

    createUser(userData, maxResponseTime, headers) {
        const response = this.post(ENDPOINTS.USERS, userData, headers, null);

        return { id: JSON.parse(response.body).id, ...userData };
    }


    getUserById(userId, maxResponseTime, headers) {
        const response = this.get(`${ENDPOINTS.USERS}/${userId}`, headers, null);

        return response.json();
    }

    getUsers(page = 1, limit = 10, maxResponseTime, headers) {
        const queryParams = `?page=${page}&limit=${limit}`;
        const response = this.get(ENDPOINTS.USERS, headers, queryParams);

        return response.json();
    }

    updateUser(userId, updatedData, maxResponseTime, headers) {
        const response = this.put(`${ENDPOINTS.USERS}/${userId}`, updatedData, headers, null);

        return response;
    }

    deleteUser(userId, maxResponseTime, headers) {
        const response = this.delete(`${ENDPOINTS.USERS}/${userId}`, headers, null);

        return response;
    }
}
