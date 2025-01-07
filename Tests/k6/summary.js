import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

export function handleSummary(data) {
    const stage = __ENV.STAGE || 'stage1';
    const version = __ENV.VERSION || 'No Version';

    const now = new Date();
    const formattedDate = now.toISOString().split('T')[0];
    const time = now.toTimeString().split(' ')[0].replace(/:/g, 'h');

    const reportPath = `./REPORTS/${version}/movies/${stage}/summary_${formattedDate}_${time}.html`;

    return {
        [reportPath]: htmlReport(data),
    };
}
