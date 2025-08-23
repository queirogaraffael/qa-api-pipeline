import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

export function generateFileName(baseName) {
    const now = new Date();
    const date = now.toISOString().split('T')[0];
    const time = now.toTimeString().split(' ')[0].replace(/:/g, '-');
    const scriptName = baseName || "summary";
    return `${scriptName}-${date}-${time}.html`;
}

export function handleSummary(data, baseName = "summary") {
    let fileName = generateFileName(baseName);
    return {
        [fileName]: htmlReport(data),
    };
}
