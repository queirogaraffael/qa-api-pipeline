import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  scenarios: {
    update_movies: {
      executor: 'constant-arrival-rate',
      rate: 50, // 50 requests per second
      timeUnit: '1s',
      duration: '1m',
      preAllocatedVUs: 25,
      maxVUs: 50,
    },
  },
  thresholds: {
    http_req_duration: ['p(95)<300'], // 95% das requisições devem ser < 300ms
  },
};

export default function () {
  const url = 'https://api.example.com/movies/123'; // Substituir pelo ID válido
  const payload = JSON.stringify({
    title: `Updated Movie ${__ITER}`,
    genre: 'Drama',
    releaseYear: 2026,
  });
  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const response = http.put(url, payload, params);

  check(response, {
    'status is 200': (r) => r.status === 200,
    'response time is < 300ms': (r) => r.timings.duration < 300,
  });

  sleep(1);
}
