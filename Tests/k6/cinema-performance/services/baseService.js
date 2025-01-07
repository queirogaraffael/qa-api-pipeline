import { EnvironmentConfig } from '../support/config/Environment.js'
import { ENVIRONMENTS } from '../support/base/constants.js';

const ENVIRONMENT = ENVIRONMENTS.AWS;
const DEFAULT_BASE_URI = EnvironmentConfig.getEnvironment(ENVIRONMENT).url;

export class BaseService {
    constructor(base_uri = DEFAULT_BASE_URI) {
        this.base_uri = base_uri;
    }
}