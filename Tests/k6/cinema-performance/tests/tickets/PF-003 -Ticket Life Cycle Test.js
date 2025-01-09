import { sleep, BaseChecks, TicketsService, SharedArray, geraTicketAleatorio } from "../../support/base/baseTest.js";
import { scenarios, thresholds } from "../../support/config/tickets/ticketsEnvironments.js";

const ticketsService = new TicketsService();
const checks = new BaseChecks();

const testType = __ENV.TEST_TYPE || "all";

export let options = {
  scenarios: scenarios(testType),
  thresholds: thresholds,
};

export default function () {
  const ticketData = geraTicketAleatorio();

  const createResponse = ticketsService.createTicket(JSON.stringify(ticketData), null);
  checks.checkResponseCreated(createResponse, "status is 201");
  checks.checkResponseTime(createResponse, 300, "<", "creation response time is < 300ms");
  sleep(1);
  const ticketId = createResponse.json()._id;

  const getResponse = ticketsService.getTicketById(ticketId, null);
  checks.checkResponse(getResponse, 200, "status get ticket is 200");
  checks.checkResponseTime(getResponse, 300, "<", "get response time is < 300ms");
  sleep(2);

  const updatedData = { ...ticketData, seatNumber: 10 };
  const updateResponse = ticketsService.updateTicket(ticketId, JSON.stringify(updatedData), null);
  checks.checkResponse(updateResponse, 200, "status is 200");
  checks.checkResponseTime(updateResponse, 300, "<", "update response time is < 300ms");
  sleep(1);

  const deleteResponse = ticketsService.deleteTicket(ticketId, null);
  checks.checkResponse(deleteResponse, 200, "status is 200");
  checks.checkResponseTime(deleteResponse, 300, "<", "delete response time is < 300ms");
  sleep(2);

  sleep(1);
};
