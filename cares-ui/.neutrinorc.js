module.exports = {
  use: [
    '@neutrinojs/standardjs',
    [
      '@neutrinojs/jest',
      {
        setupTestFrameworkScriptFile: '<rootDir>/test-setup.js'
      }
    ],
    '@neutrinojs/react'
  ]
};
