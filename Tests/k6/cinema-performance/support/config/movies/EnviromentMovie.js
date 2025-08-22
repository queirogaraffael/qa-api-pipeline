export const scenarios = {
    load_test: {
        executor: 'constant-arrival-rate',
        rate: 50,
        timeUnit: '1s',
        duration: '1m',
        preAllocatedVUs: 50,
        maxVUs: 100,
        exec: 'loadTest',
        tags: { testType: 'load_test' },
    },
    stress_test: {
        executor: 'ramping-arrival-rate',
        startRate: 10,
        timeUnit: '1s',
        stages: [
            { target: 100, duration: '2m' },
            { target: 200, duration: '2m' },
            { target: 0, duration: '1m' },
        ],
        preAllocatedVUs: 200,
        maxVUs: 300,
        exec: 'stressTest',
        tags: { testType: 'stress_test' },
    },
    peak_test: {
        executor: 'per-vu-iterations',
        vus: 100,
        iterations: 1000,
        maxDuration: '2m',
        exec: 'peakTest',
        tags: { testType: 'peak_test' },
    },
    resilience_test: {
        executor: 'shared-iterations',
        vus: 50,
        iterations: 500,
        maxDuration: '3m',
        exec: 'resilienceTest',
        tags: { testType: 'resilience_test' },
    },
    smoke_test: {
        executor: 'constant-vus',
        vus: 5,
        duration: '30s',
        exec: 'smokeTest',
        tags: { testType: 'smoke_test' },
    },
};

export const thresholds = {
    'http_req_duration{testType:load_test}': ['p(95)<500'],
    'http_req_duration{testType:stress_test}': ['p(95)<1000'],
    'http_req_duration{testType:peak_test}': ['p(95)<200'],
    'http_req_duration{testType:resilience_test}': ['p(95)<600'],
    'http_req_duration{testType:smoke_test}': ['p(95)<300'],
    'http_req_failed{testType:load_test}': ['rate<0.01'],
};
