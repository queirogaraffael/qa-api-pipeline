import { EnvironmentConfig, ENVIRONMENTS } from '../support/config/Environment.js'

const ENVIRONMENT = ENVIRONMENTS.TESTE;
const DEFAULT_BASE_URI = EnvironmentConfig.getEnvironment(ENVIRONMENT).url;

export class BaseService {
    constructor(base_uri = DEFAULT_BASE_URI) {
        this.base_uri = base_uri;
    }
}