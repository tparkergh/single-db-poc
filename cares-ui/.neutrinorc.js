module.exports = {
  use: [
    '@neutrinojs/standardjs',
    [
      '@neutrinojs/jest',
      {
        setupTestFrameworkScriptFile: '<rootDir>/test-setup.js'
      }
    ],
    '@neutrinojs/react',
    [
      '@neutrinojs/dev-server', { disableHostCheck: true }
    ],
    ['@neutrinojs/env', ['CARES_API_URL']],
    ['@neutrinojs/eslint', {
      eslint: {
        rules: {
          'new-cap': ["error", { "capIsNew": false }]
        }
      }
    }
    ]
  ]
};
