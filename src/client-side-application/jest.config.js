
module.exports = {
    testEnvironmentOptions: {
        customExportConditions: ["node", "node-addons"],
    },
    testEnvironment: "jsdom",
    transform: {
        "^.+\\.vue$": "@vue/vue3-jest",
        "^.+\\.js$": "babel-jest",
    },
    testRegex: "(/__tests__/.*|(\\.|/)(test|spec))\\.(js|ts)$",
    moduleFileExtensions: ["vue", "js"],
    moduleNameMapper: {
        "^@/(.*)$": "<rootDir>/src/$1",
    },
    coveragePathIgnorePatterns: ["/node_modules/", "/tests/"],
    coverageReporters: ["text", "json-summary"],
    moduleDirectories: ["node_modules", "src"],
    roots : [
        "<rootDir>",
        
    ],
    modulePaths: [
        "<rootDir>"
    ],

};

