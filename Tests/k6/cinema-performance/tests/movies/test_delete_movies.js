import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  scenarios: {
    delete_movies: {
      executor: 'constant-arrival-rate',
      rate: 30, // 30 requests per second
      timeUnit: '1s',
      duration: '1m',
      preAllocatedVUs: 15,
      maxVUs: 30,
    },
  },
  thresholds: {
    http_req_duration: ['p(95)<400'], // 95% das requisições devem ser < 400ms
  },
};

export default function () {
  const url = 'https://api.example.com/movies/123'; // Substituir pelo ID válido
  const response = http.del(url);

  check(response, {
    'status is 200': (r) => r.status === 200,
    'response time is < 400ms': (r) => r.timings.duration < 400,
  });

  sleep(1);
}
