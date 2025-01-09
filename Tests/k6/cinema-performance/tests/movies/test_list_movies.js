import { sleep, BaseChecks, ENVIRONMENTS, MoviesService, SharedArray } from "../../support/base/baseTest.js";

const moviesService = new MoviesService(ENVIRONMENTS.LOCAL);
const checks = new BaseChecks();

const data = new SharedArray('Movies', function () {
  const jsonData = JSON.parse(open('../../data/dynamic/movies/movies.json'));
  return jsonData.movies;
});

export let options = {
  scenarios: {
    list_movies: {
      executor: 'constant-arrival-rate',
      rate: 100,
      timeUnit: '1s',
      duration: '1m',
      preAllocatedVUs: 50,
      maxVUs: 100,
    },
  },
  thresholds: {
    http_req_duration: ['p(95)<100'],
  },
};

export function setup() {
  for (let i = 0; i < data.length; i++) {
    const movie = data[i];
    moviesService.createMovie(movie, null);
  }
}


export default function () {

  const response = moviesService.getMoviesAll();

  checks.checkResponseCreated(response, 'status is 200');
  checks.checkResponseTime(response, 100, '<', 'response time is < 100ms');

  sleep(1);
}
