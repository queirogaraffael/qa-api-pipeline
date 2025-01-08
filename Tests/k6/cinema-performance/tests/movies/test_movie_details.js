import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  scenarios: {
    movie_details: {
      executor: 'constant-arrival-rate',
      rate: 100, // 100 requests per second
      timeUnit: '1s',
      duration: '1m',
      preAllocatedVUs: 50,
      maxVUs: 100,
    },
  },
  thresholds: {
    http_req_duration: ['p(95)<50'], // 95% das requisições devem ser < 50ms
  },
};

export default function () {
  const url = 'https://api.example.com/movies/123'; // Substituir pelo ID válido
  const response = http.get(url);

  check(response, {
    'status is 200': (r) => r.status === 200,
    'response time is < 50ms': (r) => r.timings.duration < 50,
  });

  sleep(1);
}
