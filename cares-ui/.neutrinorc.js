path = require('path')

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
    ['@neutrinojs/env', ['CARES_API_URL', 'CARES_BRE_API_URL']],
    ['@neutrinojs/eslint', {
      eslint: {
        useEslintrc: true,
        plugins: ['import'],
        rules: {
          'import/no-unresolved': [2, {"commonjs": true, "amd": true}],
          'new-cap': ["error", { "capIsNew": false }]
        },
        settings: {
          "import/resolver": {
            "node": {
              "paths": [path.resolve(__dirname, 'src')],
              "extensions": [".jsx", ".js", ".json"],
            }
          }
        }
      }
    }
    ]
  ]
};
