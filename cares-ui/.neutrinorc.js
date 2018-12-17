module.exports = {
  env: {
    MODE: {
      development: {
        use: [
          '@neutrinojs/standardjs',
          [
            '@neutrinojs/jest',
            {
              setupTestFrameworkScriptFile: '<rootDir>/test-setup.js'
            }
          ]
        ]
      }
    }
  },
  use: [
    '@neutrinojs/react'
  ]
};
