import { ENVIRONMENTS } from '../support/config/Environments.js'
const DEFAULT_BASE_URI = ENVIRONMENTS.LOCAL;

export class BaseService {
    constructor(base_uri) {
        this.base_uri = base_uri || DEFAULT_BASE_URI;
    }
}
