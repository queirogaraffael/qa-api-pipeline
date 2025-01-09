export const scenarios = (testType) => {
    const scenarios = {};

    if (testType === "all" || testType === "carga") {
        scenarios.ticket_operations_load = {
            executor: "shared-iterations",
            vus: 50,
            iterations: 100,
            maxDuration: "30s",
            tags: { type: "load" },
        };
    }

    if (testType === "all" || testType === "estresse") {
        scenarios.ticket_operations_stress = {
            executor: "shared-iterations",
            vus: 100,
            iterations: 100,
            maxDuration: "30s",
            tags: { type: "stress" },
        };
    }

    if (testType === "all" || testType === "pico") {
        scenarios.ticket_operations_peak = {
            executor: "shared-iterations",
            vus: 200,
            iterations: 200,
            maxDuration: "20s",
            tags: { type: "peak" },
        };
    }

    if (testType === "all" || testType === "resiliencia") {
        scenarios.ticket_operations_resilience = {
            executor: "shared-iterations",
            vus: 50,
            iterations: 50,
            maxDuration: "40s",
            tags: { type: "resilience" },
        };
    }

    if (testType === "all" || testType === "volume") {
        scenarios.ticket_operations_volume = {
            executor: "shared-iterations",
            vus: 20,
            iterations: 200,
            maxDuration: "60s",
            tags: { type: "volume" },
        };
    }

    return scenarios;
};

export const thresholds = {
    "http_req_duration{type:load}": ["avg<250", "p(95)<400"],
    "http_req_duration{type:stress}": ["avg<500", "p(95)<700"],
    "http_req_duration{type:peak}": ["avg<800", "p(95)<1000"],
    "http_req_duration{type:resilience}": ["avg<400", "p(95)<600"],
    "http_req_duration{type:volume}": ["avg<1000", "p(95)<1200"],
};
