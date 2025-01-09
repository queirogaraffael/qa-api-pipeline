export { ENDPOINTS, ENVIRONMENTS } from './constants.js'
export { handleSummary as importedHandleSummary } from '../../services/summary.js';
export { BaseChecks } from './baseCheck.js';
export { BaseRest } from '../../services/BaseRest.js';
export {sleep} from 'k6';
export { MoviesService} from '../../services/movies/movieService.js'
export { geraFilmeAleatorio} from '../../data/dynamic/movies/geraFilmeAleatorio.js'
export {SharedArray} from 'k6/data';