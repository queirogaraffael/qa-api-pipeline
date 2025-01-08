import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  scenarios: {
    create_movies: {
      executor: 'constant-arrival-rate',
      rate: 100, // 100 requests per second
      timeUnit: '1s',
      duration: '1m',
      preAllocatedVUs: 50,
      maxVUs: 100,
    },
  },
  thresholds: {
    http_req_duration: ['p(95)<200'], // 95% das requisições devem ser < 200ms
  },
};

export default function () {
  const url = 'https://api.example.com/movies';
  const payload = JSON.stringify({
    title: `Movie ${__ITER}`,
    genre: 'Action',
    releaseYear: 2025,
  });
  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const response = http.post(url, payload, params);

  check(response, {
    'status is 201': (r) => r.status === 201,
    'response time is < 200ms': (r) => r.timings.duration < 200,
  });

  sleep(1);
}
