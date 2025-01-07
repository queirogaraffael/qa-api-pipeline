import { ENVIRONMENTS } from "../../support/base/baseTest.js";

export class EnvironmentConfig {
    static environments = {
        [ENVIRONMENTS.LOCAL]: {
            url: "http://localhost:3000"
        },
        [ENVIRONMENTS.AWS]: {
            url: ""
        }
    };

    static getEnvironment(env = ENVIRONMENTS.TESTE) {
        if (!this.environments[env]) {
            throw new Error(
                `Ambiente "${env}" não é válido. Ambientes válidos: ${Object.values(ENVIRONMENTS).join(", ")}.`
            );
        }
        return this.environments[env];
    }
}
