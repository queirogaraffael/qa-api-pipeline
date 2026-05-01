import { BaseRest, ENDPOINTS } from '../../support/base/baseTest.js';

export class TicketsService extends BaseRest {
    constructor(environment) {
        super(environment);
    }

    createTicket(ticketData, headers) {
        return this.post(ENDPOINTS.TICKETS, ticketData, headers);
    }

    getTickets(page = 1, limit = 10, headers) {
        const queryParams = `?page=${page}&limit=${limit}`;
        return this.get(ENDPOINTS.TICKETS, headers, queryParams);
    }

    getTicketById(ticketId, headers) {
        return this.get(`${ENDPOINTS.TICKETS}/${ticketId}`, headers);
    }

    updateTicket(ticketId, updatedData, headers) {
        return this.put(`${ENDPOINTS.TICKETS}/${ticketId}`, updatedData, headers);
    }

    deleteTicket(ticketId, headers) {
        return this.delete(`${ENDPOINTS.TICKETS}/${ticketId}`, headers);
    }
}
