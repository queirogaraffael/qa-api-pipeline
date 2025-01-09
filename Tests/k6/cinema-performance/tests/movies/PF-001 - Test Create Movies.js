import { sleep, BaseChecks, MoviesService, geraFilmeAleatorio } from "../../support/base/baseTest.js";

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

  console.log(response.status);

  checks.checkResponseCreated(response, 'status is 201');
  checks.checkResponseTime(response, 200, '<', 'response time is < 200ms');

  sleep(1);
}
