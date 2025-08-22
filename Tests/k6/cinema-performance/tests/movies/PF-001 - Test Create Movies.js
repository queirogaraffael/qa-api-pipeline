import { sleep, BaseChecks, MoviesService, geraFilmeAleatorio, importedHandleSummary } from "../../support/base/baseTest.js";

export function handleSummary(data) {
  return importedHandleSummary(data, "PF-001");
}

const moviesService = new MoviesService();

const checks = new BaseChecks();

export let options = {
  scenarios: {
    create_movies: {
      executor: 'constant-arrival-rate',
      rate: 100,
      timeUnit: '1s',
      duration: '1m',
      preAllocatedVUs: 50,
      maxVUs: 100,
    },
  },
  thresholds: {
    http_req_duration: ['p(95)<200'],
  },
};

export default function () {

  const payload = geraFilmeAleatorio();

  const response = moviesService.createMovie(JSON.stringify(payload), null);

  checks.checkResponseCreated(response, 'status is 201');
  checks.checkResponseTime(response, 200, '<', 'response time is < 200ms');

  /*Const specificHeader = 'Content-Type';
if (response.headers[specificHeader]) {
  console.log(`Header "${specificHeader}" encontrado: ${response.headers[specificHeader]}`);
} else {
  console.log(`Header "${specificHeader}" não encontrado.`);
} */

  sleep(1);
}
