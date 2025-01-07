// Environment Configuration
export const BASE_URL = 'http://localhost:3000';

// Configuration for the test stages
export const stagesConfig = {
    stage1: [
        { duration: '1s', target: 30 },
        { duration: '10s', target: 30 }
    ],
    stage2: [
        { duration: '1s', target: 50 },
        { duration: '10s', target: 50 }
    ],
    stage3: [
        { duration: '1s', target: 100 },
        { duration: '10s', target: 100 }
    ],
};
