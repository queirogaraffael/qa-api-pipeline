import { BaseChecks, BaseRest, ENDPOINTS } from '../../support/base/baseTest.js';

export class TicketsService extends BaseRest {
    constructor(environment) {
        super(environment);
        this.checks = new BaseChecks();
    }

    createTicket(ticketData, maxResponseTime, headers) {
        const response = this.post(ENDPOINTS.TICKETS, ticketData, headers);
    
        this.checks.checkStatusCode(response, 201, 'POST /tickets resposta tem status 201');
        this.checks.checkResponseTime(response, maxResponseTime, 'POST /tickets tempo de resposta está dentro do limite');
        this.checks.checkResponseBodyContains(response, '_id', 'POST /tickets corpo da resposta contém ID do ticket');
    
        return { id: JSON.parse(response.body)._id, ...ticketData };
    }

    getTickets(page = 1, limit = 10, maxResponseTime, headers) {
        const queryParams = `?page=${page}&limit=${limit}`;
        const response = this.get(ENDPOINTS.TICKETS, headers, queryParams);
    
        this.checks.checkStatusCode(response, 200, 'GET /tickets resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'GET /tickets tempo de resposta é <= maxResponseTime');
    
        return response.json();
    }

    getTicketById(ticketId, maxResponseTime, headers) {
        const response = this.get(`${ENDPOINTS.TICKETS}/${ticketId}`, headers, null);
    
        this.checks.checkStatusCode(response, 200, 'GET /tickets/{id} resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'GET /tickets/{id} tempo de resposta é <= maxResponseTime');
    
        return response.json();
    }
    
    updateTicket(ticketId, updatedData, maxResponseTime, headers) {
        const response = this.put(`${ENDPOINTS.TICKETS}/${ticketId}`, updatedData, headers, null);
    
        this.checks.checkStatusCode(response, 200, 'PUT /tickets resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'PUT /tickets tempo de resposta é <= maxResponseTime');
    
        return response;
    }

    deleteTicket(ticketId, maxResponseTime, headers) {
        const response = this.delete(`${ENDPOINTS.TICKETS}/${ticketId}`, headers);

        this.checks.checkStatusCode(response, 200, 'DELETE /tickets resposta tem status 200');
        this.checks.checkResponseTime(response, maxResponseTime, 'DELETE /tickets tempo de resposta é <= maxResponseTime');

        return response;
    }
}
