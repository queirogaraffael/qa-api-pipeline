import { sleep, BaseChecks, TicketsService, geraTicketAleatorio, importedHandleSummary } from "../../support/base/baseTest.js";

export function handleSummary(data) {
  return importedHandleSummary(data, "PF-004");
}

const ticketsService = new TicketsService();

const checks = new BaseChecks();

export let options = {
  scenarios: {
    reserve_tickets: {
      executor: 'constant-arrival-rate',
      rate: 50,
      timeUnit: '1s',
      duration: '1m',
      preAllocatedVUs: 50,
      maxVUs: 100,
    },
  },
  thresholds: {
    http_req_duration: ['avg<300'],
    'http_req_duration{status:201}': ['p(95)<300'],
  },
};

export default function () {
  const payload = geraTicketAleatorio();

  const response = ticketsService.createTicket(JSON.stringify(payload), null);

  console.log(response.status);

  checks.checkResponseCreated(response, 'status created is 201');
  checks.checkResponseTime(response, 300, '<', 'response time is < 300ms');

  sleep(1);
}
