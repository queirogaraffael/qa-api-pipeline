import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  scenarios: {
    list_movies: {
      executor: 'constant-arrival-rate',
      rate: 100, // 100 requests per second
      timeUnit: '1s',
      duration: '1m',
      preAllocatedVUs: 50,
      maxVUs: 100,
    },
  },
  thresholds: {
    http_req_duration: ['p(95)<100'], // 95% das requisições devem ser < 100ms
  },
};

export default function () {
  const url = 'https://api.example.com/movies?page=1&limit=10';
  const response = http.get(url);

  check(response, {
    'status is 200': (r) => r.status === 200,
    'response time is < 100ms': (r) => r.timings.duration < 100,
  });

  sleep(1);
}
