import { sleep, BaseChecks, TicketsService, geraTicketAleatorio, importedHandleSummary } from "../../support/base/baseTest.js";
import { scenarios, thresholds } from "../../support/config/tickets/ticketsEnvironments.js";

export function handleSummary(data) {
  return importedHandleSummary(data, "PF-003");
}

const checks = new BaseChecks();
const serviceUrl = __ENV.SERVICE_URL || undefined;
const ticketsService = new TicketsService(serviceUrl);

export let options = {
  scenarios: scenarios(__ENV.TEST_TYPE || "all"),
  thresholds: thresholds,
};

function executeTestFlow(testType) {
  const ticketData = geraTicketAleatorio();
  const tags = { testType };

  const createResponse = ticketsService.createTicket(JSON.stringify(ticketData), null);
  checks.checkResponseCreated(createResponse, "status is 201", tags);
  checks.checkResponseTime(createResponse, 300, "<", "creation response time is < 300ms", tags);
  sleep(1);

  const ticketId = createResponse.json()._id;

  const getResponse = ticketsService.getTicketById(ticketId, null);
  checks.checkResponse(getResponse, 200, "status get ticket is 200", tags);
  checks.checkResponseTime(getResponse, 300, "<", "get response time is < 300ms", tags);
  sleep(2);

  const updatedData = { ...ticketData, seatNumber: 10 };
  const updateResponse = ticketsService.updateTicket(ticketId, JSON.stringify(updatedData), null);
  checks.checkResponse(updateResponse, 200, "status is 200", tags);
  checks.checkResponseTime(updateResponse, 300, "<", "update response time is < 300ms", tags);
  sleep(1);

  const deleteResponse = ticketsService.deleteTicket(ticketId, null);
  checks.checkResponse(deleteResponse, 200, "status is 200", tags);
  checks.checkResponseTime(deleteResponse, 300, "<", "delete response time is < 300ms", tags);
  sleep(2);
}

export function ticket_operations_load() {
  executeTestFlow("carga");
}

export function ticket_operations_stress() {
  executeTestFlow("estresse");
}

export function ticket_operations_peak() {
  executeTestFlow("pico");
}

export function ticket_operations_resilience() {
  executeTestFlow("resiliencia");
}

export function ticket_operations_volume() {
  executeTestFlow("volume");
}
