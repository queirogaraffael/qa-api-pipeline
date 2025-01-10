export const scenarios = (testType) => {
    const scenariosConfig = {};

    if (testType === "all" || testType === "carga") {
        scenariosConfig.ticket_operations_load = {
            executor: "shared-iterations",
            vus: 50,
            iterations: 100,
            maxDuration: "30s",
            exec: "ticket_operations_load",
            tags: { testType: "carga" },
        };
    }

    if (testType === "all" || testType === "estresse") {
        scenariosConfig.ticket_operations_stress = {
            executor: "shared-iterations",
            vus: 100,
            iterations: 100,
            maxDuration: "30s",
            exec: "ticket_operations_stress", 
            tags: { testType: "estresse" },
        };
    }

    if (testType === "all" || testType === "pico") {
        scenariosConfig.ticket_operations_peak = {
            executor: "shared-iterations",
            vus: 200,
            iterations: 200,
            maxDuration: "20s",
            exec: "ticket_operations_peak", 
            tags: { testType: "pico" },
        };
    }

    if (testType === "all" || testType === "resiliencia") {
        scenariosConfig.ticket_operations_resilience = {
            executor: "shared-iterations",
            vus: 50,
            iterations: 50,
            maxDuration: "40s",
            exec: "ticket_operations_resilience", 
            tags: { testType: "resiliencia" },
        };
    }

    if (testType === "all" || testType === "volume") {
        scenariosConfig.ticket_operations_volume = {
            executor: "shared-iterations",
            vus: 20,
            iterations: 200,
            maxDuration: "60s",
            exec: "ticket_operations_volume",
            tags: { testType: "volume" },
        };
    }

    return scenariosConfig;
};


export const thresholds = {
    "http_req_duration{testType:load}": ["avg<250", "p(95)<400"], 
    "http_req_duration{testType:stress}": ["avg<500", "p(95)<700"], 
    "http_req_duration{testType:peak}": ["avg<800", "p(95)<1000"],
    "http_req_duration{testType:resilience}": ["avg<400", "p(95)<600"],
    "http_req_duration{testType:volume}": ["avg<1000", "p(95)<1200"],

};
